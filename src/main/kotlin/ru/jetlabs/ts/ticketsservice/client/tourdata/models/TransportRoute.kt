package ru.jetlabs.ts.ticketsservice.client.tourdata.models

import java.time.LocalDateTime

data class TransportRoute(
    val id: Long,
    val name: String,
    val transport: Transport,
    val departurePlace: Place,
    val arrivePlace: Place,
    val departureTime: LocalDateTime,
    val arrivalTime: LocalDateTime,
    val cost: Double,
    val capacity: Int
)