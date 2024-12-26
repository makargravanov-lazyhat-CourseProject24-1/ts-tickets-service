package ru.jetlabs.ts.ticketsservice.models

data class RegisterTicketForm(
    val tour: Tour,
    val userId: Long
)