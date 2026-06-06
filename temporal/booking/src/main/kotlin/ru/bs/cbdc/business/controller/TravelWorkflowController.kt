package ru.bs.cbdc.business.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.bs.cbdc.business.dto.TravelRequest
import ru.bs.cbdc.business.starter.TravelBookingWorkflowStarter
import java.util.UUID

@RestController
class TravelWorkflowController(val starter: TravelBookingWorkflowStarter) {


    @PostMapping("/book")
    fun bookTravel(@RequestBody travelRequest: TravelRequest): ResponseEntity<String> {
        starter.startWorkFlow(travelRequest)
        return ResponseEntity.ok("Travel booking workflow started for user: " + travelRequest.userId)
    }

    @PostMapping("/confirm/{userId}")
    fun confirmBooking(@PathVariable userId: String): ResponseEntity<String> {
        starter.sendConfirmationSignal(userId)
        return ResponseEntity.ok("✅ Booking confirmed by user!")
    }
}
