package ru.bs.cbdc.business.dto

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class TravelRequest @JsonCreator(mode = JsonCreator.Mode.PROPERTIES) constructor(
    @JsonProperty("userId") var userId: String,
    @JsonProperty("destination") val destination: String,
    @JsonProperty("travelDate") val travelDate: String
)