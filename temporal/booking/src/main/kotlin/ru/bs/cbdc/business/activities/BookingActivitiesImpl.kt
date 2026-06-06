package ru.bs.cbdc.business.activities

import org.springframework.stereotype.Service
import ru.bs.cbdc.business.dto.TravelRequest

@Service
class BookingActivitiesImpl : BookingActivities {

    override fun book(travelRequest: TravelRequest) {
        println(
            "Booked for user: ${travelRequest.userId}}"
        )
    }
}
