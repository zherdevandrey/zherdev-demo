package ru.bs.cbdc.business.activities

import org.springframework.stereotype.Service
import ru.bs.cbdc.business.dto.TravelRequest
import java.util.*

@Service
class PaymentActivitiesImpl : PaymentActivities {

    override fun payment(travelRequest: TravelRequest) {
        println(
            "Payment for user: ${travelRequest.userId}}"
        )
    }
}
