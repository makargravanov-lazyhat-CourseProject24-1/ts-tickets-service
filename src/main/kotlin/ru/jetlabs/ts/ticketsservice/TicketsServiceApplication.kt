package ru.jetlabs.ts.ticketsservice

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import ru.jetlabs.ts.ticketsservice.daos.TicketDao
import ru.jetlabs.ts.ticketsservice.models.Ticket
import ru.jetlabs.ts.ticketsservice.models.mapToTicket
import ru.jetlabs.ts.ticketsservice.tables.Tickets

@SpringBootApplication
class TicketsServiceApplication

fun main(args: Array<String>) {
    runApplication<TicketsServiceApplication>(*args)
}

object TicketRouteBindings : LongIdTable("ticket_routes_bindings") {
    val ticket = reference("ticket", Tickets)
    val routeId = long("route_id")
}

class TicketRouteBindingDao(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<TicketRouteBindingDao>(TicketRouteBindings)

    var ticket by TicketDao referencedOn TicketRouteBindings.ticket
    var routeId by TicketRouteBindings.routeId
}

data class TicketRouteBinding(
    val id: Long,
    val ticket: Ticket,
    val routeId: Long,
)

fun TicketRouteBindingDao.mapToTicketRouteBinding() = TicketRouteBinding(
    id = id.value,
    ticket = ticket.mapToTicket(),
    routeId = routeId
)

