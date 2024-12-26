package ru.jetlabs.ts.ticketsservice.client.tourdata.models

import ru.jetlabs.ts.ticketsservice.client.tourdata.models.enums.HotelLevel

data class Hotel(
    val id: Long,
    val name: String,
    val level: HotelLevel,
    val place: Place
)