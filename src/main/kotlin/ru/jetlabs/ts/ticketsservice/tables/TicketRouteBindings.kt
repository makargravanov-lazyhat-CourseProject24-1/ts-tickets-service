package ru.jetlabs.ts.ticketsservice.tables

import org.jetbrains.exposed.dao.id.LongIdTable

object TicketRouteBindings : LongIdTable("ticket_routes_bindings") {
    val ticket = reference("ticket", Tickets)
    val routeId = long("route_id")
}