package com.example.temporal

import io.temporal.workflow.*
import java.time.Duration
import java.util.concurrent.atomic.AtomicBoolean

@WorkflowInterface
interface TemporalWorkflow {
    @WorkflowMethod
    fun start(): String

    @QueryMethod(name = "query")
    fun query(): String?

    @SignalMethod
    fun proceed()

}


class TemporalWorkflowImpl : TemporalWorkflow {
    private var isContinue = false
    private var result:String?  = null

    override fun start(): String {
        println("STARTED")
//        Workflow.sleep(Duration.ofSeconds(100))
        return "RESULT ${System.currentTimeMillis()}"
    }

    override fun query(): String? {
        return result
    }

    override fun proceed() {
        isContinue = true
        println("PROCEED")
    }

}