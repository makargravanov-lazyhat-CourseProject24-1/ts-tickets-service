package ru.jetlabs.ts.ticketsservice.tables

import org.jetbrains.exposed.dao.id.LongIdTable

object AdditionalUsers : LongIdTable("additional_users") {
    val ticketId = reference("ticket_id", Tickets)
    val userId = long("user_id")
}