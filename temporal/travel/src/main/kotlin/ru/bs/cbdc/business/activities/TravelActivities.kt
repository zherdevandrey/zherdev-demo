package ru.bs.cbdc.business.activities

import io.temporal.activity.ActivityInterface
import ru.bs.cbdc.business.dto.TravelRequest

@ActivityInterface
interface TravelActivities {
    fun bookFlight(travelRequest: TravelRequest):String

    fun cancelFlight(travelRequest: TravelRequest)

    fun bookHotel(travelRequest: TravelRequest, bookingReference:String)

    fun cancelHotel(travelRequest: TravelRequest)

    fun arrangeTransport(travelRequest: TravelRequest)

    fun cancelTransport(travelRequest: TravelRequest)

    fun cancelBooking(travelRequest: TravelRequest)

    fun confirmBooking(travelRequest: TravelRequest)
}
