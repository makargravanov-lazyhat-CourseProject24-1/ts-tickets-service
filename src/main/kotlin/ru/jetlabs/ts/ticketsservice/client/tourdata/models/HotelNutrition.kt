package ru.jetlabs.ts.ticketsservice.client.tourdata.models

import ru.jetlabs.ts.ticketsservice.client.tourdata.models.enums.NutritionType

data class HotelNutrition(
    val id: Long,
    val type: NutritionType,
    val costPerDay: Double
)