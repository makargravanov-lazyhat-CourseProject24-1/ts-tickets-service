package ru.jetlabs.ts.ticketsservice.models

import java.time.LocalDate

data class Tour(
    val id: Long,
    val agencyId: Long,
    val name: String,
    val description: String,
    val hotelId: Long,
    val roomId: Long,
    val nutritionId: Long,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val personCount: Int,
)