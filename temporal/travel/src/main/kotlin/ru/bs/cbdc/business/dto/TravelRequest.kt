package ru.bs.cbdc.business.dto

data class TravelRequest(
    val userId: String,
    val destination: String,
    val travelDate: String
)