package ru.jetlabs.ts.ticketsservice.models

import ru.jetlabs.ts.ticketsservice.daos.TicketRouteBindingDao

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