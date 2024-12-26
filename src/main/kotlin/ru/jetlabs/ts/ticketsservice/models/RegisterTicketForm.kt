package ru.jetlabs.ts.ticketsservice.models

data class RegisterTicketForm(
    val tourId: Long,
    val cost: Float,
    val userId: Long
)