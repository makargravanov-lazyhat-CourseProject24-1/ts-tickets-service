package ru.jetlabs.ts.ticketsservice.models

import java.time.LocalDateTime

data class TicketStatusLog(
    val id: Long,
    val ticketId: Long,
    val status: TicketStatus,
    val createdAt: LocalDateTime,
)