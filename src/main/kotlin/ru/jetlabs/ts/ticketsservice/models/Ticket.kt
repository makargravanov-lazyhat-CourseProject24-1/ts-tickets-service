package ru.jetlabs.ts.ticketsservice.models

import ru.jetlabs.ts.ticketsservice.daos.TicketDao
import java.time.LocalDate
import java.time.LocalDateTime

data class Ticket(
    val id: Long,
    val tourId: Long,
    val userId: Long,
    val tourCost: Double,
    val transportCost: Double?,
    val createdAt: LocalDateTime,
    val additionalUsers: List<Long>,
    val endDate: LocalDate,
    val startDate: LocalDate
)

fun TicketDao.mapToTicket(): Ticket = Ticket(
    id = id.value,
    tourId = tourId,
    userId = userId,
    tourCost = tourCost,
    transportCost = transportCost,
    startDate = startDate,
    endDate = endDate,
    createdAt = createdAt,
    additionalUsers = additionalUsers.map { userId }.toList()
)