package ru.bs.cbdc.business.starter

import io.temporal.client.WorkflowClient
import io.temporal.client.WorkflowOptions
import io.temporal.serviceclient.WorkflowServiceStubs
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import ru.bs.cbdc.business.dto.TravelRequest
import ru.bs.cbdc.business.workflow.TravelWorkflow

@Component
class TravelBookingWorkflowStarter {
    @Autowired
    private val serviceStubs: WorkflowServiceStubs? = null


    fun startWorkFlow(travelRequest: TravelRequest) {
        val client = WorkflowClient.newInstance(serviceStubs)

        val workflow: TravelWorkflow = client.newWorkflowStub(
            TravelWorkflow::class.java,
            WorkflowOptions.newBuilder()
                .setTaskQueue("TRAVEL_TASK_QUEUE")
                .setWorkflowId("travel_" + travelRequest.userId)
                .build()
        )

        WorkflowClient.start(workflow::bookTrip, travelRequest)
    }


    fun sendConfirmationSignal(userId: String) {
        val client = WorkflowClient.newInstance(serviceStubs)

        val workflowId = "travel_$userId"
        val workflow: TravelWorkflow = client.newWorkflowStub(TravelWorkflow::class.java, workflowId)

        workflow.sendConfirmationSignal()
    }
}
