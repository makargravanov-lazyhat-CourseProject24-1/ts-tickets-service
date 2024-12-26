package ru.jetlabs.ts.ticketsservice.tables

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.javatime.datetime
import ru.jetlabs.ts.ticketsservice.models.TicketStatus
import java.time.LocalDateTime

object TicketStatusLogs : LongIdTable("ticket_status_logs") {
    val ticketId = reference("ticket_id", Tickets)
    val status = enumeration<TicketStatus>("status")
    val createdAt = datetime("created_at").clientDefault { LocalDateTime.now() }
}