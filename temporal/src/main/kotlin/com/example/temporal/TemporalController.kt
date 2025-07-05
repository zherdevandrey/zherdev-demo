package com.example.temporal

import io.temporal.api.common.v1.WorkflowExecution
import io.temporal.client.WorkflowClient
import io.temporal.client.WorkflowOptions
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import kotlin.random.Random

@RestController
class TemporalController(private val workflowClient: WorkflowClient) {

    private var workflowExecution: WorkflowExecution? = null
    var id:String? = null

    @GetMapping("/start")
    fun start():String? {

        id = Random.nextInt().toString()

        val stub1 = workflowClient.newWorkflowStub(
            TemporalWorkflow::class.java,
            WorkflowOptions.newBuilder()
                .setTaskQueue("spring-task-queue")
                .setWorkflowId(id)
                .build()
        )


        return stub1.start()
    }

    @GetMapping("/proceed")
    fun proceed() {
        val stub = workflowClient.newWorkflowStub(
            TemporalWorkflow::class.java, id
        )
        stub.proceed()
    }
}