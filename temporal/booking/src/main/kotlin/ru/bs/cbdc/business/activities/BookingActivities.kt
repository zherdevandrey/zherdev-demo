package ru.bs.cbdc.business.activities

import io.temporal.activity.ActivityInterface
import ru.bs.cbdc.business.dto.TravelRequest

@ActivityInterface
interface BookingActivities {
    fun book(travelRequest: TravelRequest)
}
