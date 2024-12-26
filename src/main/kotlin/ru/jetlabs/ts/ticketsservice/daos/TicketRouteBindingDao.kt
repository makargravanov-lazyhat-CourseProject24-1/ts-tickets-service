package ru.jetlabs.ts.ticketsservice.daos

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import ru.jetlabs.ts.ticketsservice.tables.TicketRouteBindings

class TicketRouteBindingDao(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<TicketRouteBindingDao>(TicketRouteBindings)

    var ticket by TicketDao referencedOn TicketRouteBindings.ticket
    var routeId by TicketRouteBindings.routeId
}