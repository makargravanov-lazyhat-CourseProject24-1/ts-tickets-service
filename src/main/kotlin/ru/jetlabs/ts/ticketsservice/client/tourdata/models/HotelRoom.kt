package ru.jetlabs.ts.ticketsservice.client.tourdata.models

import ru.jetlabs.ts.ticketsservice.client.tourdata.models.enums.RoomCapacity
import ru.jetlabs.ts.ticketsservice.client.tourdata.models.enums.RoomType

data class HotelRoom(
    val id: Long,
    val capacity: RoomCapacity,
    val type: RoomType,
    val wifi: Boolean,
    val costPerDay: Double
)