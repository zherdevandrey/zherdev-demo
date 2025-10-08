package ru.bs.cbdc.business.workflow

import io.temporal.workflow.SignalMethod
import io.temporal.workflow.WorkflowInterface
import io.temporal.workflow.WorkflowMethod
import ru.bs.cbdc.business.dto.TravelRequest

@WorkflowInterface
interface TravelWorkflow {
    @WorkflowMethod
    fun bookTrip(travelRequest: TravelRequest)


    @SignalMethod
    fun sendConfirmationSignal()
}
