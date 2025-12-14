package ru.bs.cbdc.business.workflow

import io.temporal.activity.ActivityOptions
import io.temporal.common.RetryOptions
import io.temporal.workflow.Functions
import io.temporal.workflow.Saga
import io.temporal.workflow.SignalMethod
import io.temporal.workflow.Workflow
import org.springframework.stereotype.Service
import ru.bs.cbdc.business.activities.TravelActivities
import ru.bs.cbdc.business.dto.TravelRequest
import java.time.Duration
import kotlin.random.Random

@Service
class TravelWorkflowImpl : TravelWorkflow {
    private var isUserConfirmed = false

    @SignalMethod
    override fun sendConfirmationSignal() {
        println("📩 Received user confirmation signal.")
        isUserConfirmed = true
    }


    override fun bookTrip(travelRequest: TravelRequest) {
        println("🚀 Starting travel booking for user: ${travelRequest.userId}")

        val activities: TravelActivities = Workflow.newActivityStub<TravelActivities>(
            TravelActivities::class.java,
            ActivityOptions.newBuilder()
                .setRetryOptions(
                    RetryOptions.newBuilder()
                        .setMaximumAttempts(3)
                        .build()
                )
                .setStartToCloseTimeout(Duration.ofSeconds(10))
                .build()
        )

        val sagaOptions = Saga.Options.Builder()
            .setParallelCompensation(false)
            .build()

        val saga = Saga(sagaOptions)

        try {
            val bookingReference = activities.bookFlight(travelRequest)
            saga.addCompensation(Functions.Func<Any> { activities.cancelFlight(travelRequest) })

            activities.bookHotel(travelRequest, bookingReference)
            saga.addCompensation(Functions.Func<Any> { activities.cancelHotel(travelRequest) })

            val nextInt = Random.nextInt()

            if (nextInt % 2 == 0) {
                activities.arrangeTransport(travelRequest)
                saga.addCompensation(Functions.Func<Any> { activities.cancelTransport(travelRequest) })
            }

            // 24 hours (1 day) -> wait for user confirmation if you won't
            // get any withing 24hr then cancel it
            println("⏳ Waiting for user confirmation for 2 min...")

            val isConfirmed = Workflow.await(
                Duration.ofMinutes(20)
            ) { isUserConfirmed }


        if (!isConfirmed) {
                println(
                    "🛑 User did not confirm within 2 minutes, cancelling the booking for user: #{travelRequest.userId}"
                )
                //cancel the booking
                activities.cancelBooking(travelRequest)
            } else {
                println("✅ User confirmed the booking: ${travelRequest.userId}")
                //confirm the booking
                activities.confirmBooking(travelRequest)
            }
        } catch (e: Exception) {
            println("❌ Error during travel booking for user: ${travelRequest.userId}. Initiating compensation.")
            saga.compensate()
        }

        println("✅ Travel booking completed for user: ${travelRequest.userId}")
    }
}
