package ru.bs.cbdc.business.workflow

import io.temporal.activity.ActivityOptions
import io.temporal.common.RetryOptions
import io.temporal.workflow.SignalMethod
import io.temporal.workflow.Workflow
import org.springframework.stereotype.Service
import ru.bs.cbdc.business.activities.BookingActivities
import ru.bs.cbdc.business.activities.PaymentActivities
import ru.bs.cbdc.business.dto.TravelRequest
import java.time.Duration

@Service
class TravelWorkflowImpl : TravelWorkflow {
    private var isUserConfirmed = false

    private val bookingActivities by lazy { Workflow.newActivityStub(BookingActivities::class.java, defaultActivityOptions) }

    @SignalMethod
    override fun sendConfirmationSignal() {
        println("📩 Received user confirmation signal.")
        isUserConfirmed = true
    }


    override fun bookTrip(travelRequest: TravelRequest) {
        println("🚀 Starting travel booking for user: ${travelRequest.userId}")

        val bookingActivities: BookingActivities = Workflow.newActivityStub(
            BookingActivities::class.java,
            ActivityOptions.newBuilder()
                .setStartToCloseTimeout(Duration.ofSeconds(10))
                .build()
        )

        val paymentActivities: PaymentActivities = Workflow.newActivityStub(
            PaymentActivities::class.java,
            ActivityOptions.newBuilder()
                .setStartToCloseTimeout(Duration.ofSeconds(10))
                .build()
        )

        bookingActivities.book(travelRequest)

        paymentActivities.payment(travelRequest)

//        val isConfirmed = Workflow.await(
//            Duration.ofMinutes(20)
//        ) { isUserConfirmed }
//
//
//        if (isConfirmed) {
//            paymentActivities.payment(travelRequest)
//        }

        println("✅ Travel booking completed for user: ${travelRequest.userId}")
    }
}

val defaultActivityOptions = ActivityOptions.newBuilder()
    .setStartToCloseTimeout(Duration.ofSeconds(1000))
    .build()
