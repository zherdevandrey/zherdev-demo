package ru.bs.cbdc.business.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.bs.cbdc.business.dto.TravelRequest
import ru.bs.cbdc.business.starter.TravelBookingWorkflowStarter

@RestController
@RequestMapping("/travel")
class TravelWorkflowController(starter: TravelBookingWorkflowStarter) {
    private val starter: TravelBookingWorkflowStarter = starter

    // Endpoint to start the travel booking workflow
    @PostMapping("/book")
    fun bookTravel(@RequestBody travelRequest: TravelRequest): ResponseEntity<String> {
        starter.startWorkFlow(travelRequest)
        return ResponseEntity.ok("Travel booking workflow started for user: " + travelRequest.userId)
    }

    // Endpoint to confirm the booking by sending a signal to the workflow
    @PostMapping("/confirm/{userId}")
    fun confirmBooking(@RequestParam userId: String): ResponseEntity<String> {
        starter.sendConfirmationSignal(userId)
        return ResponseEntity.ok("✅ Booking confirmed by user!")
    }
}
