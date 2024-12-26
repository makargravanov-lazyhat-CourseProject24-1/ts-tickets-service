package ru.jetlabs.ts.ticketsservice.daos

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import ru.jetlabs.ts.ticketsservice.tables.TicketStatusLogs

class TicketStatusLogDao(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<TicketStatusLogDao>(TicketStatusLogs)

    var ticketId by TicketStatusLogs.ticketId
    var status by TicketStatusLogs.status
    var createdAt by TicketStatusLogs.createdAt
}