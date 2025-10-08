package ru.bs.cbdc.business.activities

import org.springframework.stereotype.Service
import ru.bs.cbdc.business.dto.TravelRequest
import java.util.*

@Service
class TravelActivitiesImpl : TravelActivities {

    override fun bookFlight(travelRequest: TravelRequest):String {
        println(
            "Flight booked for user: ${travelRequest.userId} to destination: ${travelRequest.destination} on date: ${travelRequest.travelDate}"
        )
        return UUID.randomUUID().toString()
    }

    override fun cancelFlight(travelRequest: TravelRequest) {
        println(
            "🛑 Cancelling flight for user ${ travelRequest.userId} because of failure"
        )
    }

    override fun bookHotel(travelRequest: TravelRequest, bookingReference:String) {
        // gRPC call to hotel service
        println(
            "Hotel booked for user: ${travelRequest.userId} at destination: ${travelRequest.destination} on date: ${travelRequest.travelDate}"
        )
    }

    override fun cancelHotel(travelRequest: TravelRequest) {
        println(
            "🛑 Cancelling hotel for user ${travelRequest.userId} because of failure"
        )
    }

    override fun arrangeTransport(travelRequest: TravelRequest) {
        // Kafka message to transport service

        println(
            "Transport arranged for user: ${travelRequest.userId} at destination: ${travelRequest.destination} on date: ${travelRequest.travelDate}"
        )

        //simulate a failure to demonstrate compensation
        throw RuntimeException("Simulated transport arrangement failure!")
    }

    override fun cancelTransport(travelRequest: TravelRequest) {
        println(
            "🛑 Cancelling transport for user ${travelRequest.userId}"
        )
    }

    override fun cancelBooking(travelRequest: TravelRequest) {
        println(
            "Cancelling booking for user: ${travelRequest.userId} at destination: ${travelRequest.destination} on date: ${travelRequest.travelDate}"
        )
    }

    override fun confirmBooking(travelRequest: TravelRequest) {
        println(
            "Booking confirmed for user: ${travelRequest.userId} at destination: ${travelRequest.destination} on date: ${travelRequest.travelDate}"
        )
    }
}
