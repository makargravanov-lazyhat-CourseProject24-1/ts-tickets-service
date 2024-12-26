package ru.jetlabs.ts.ticketsservice.daos

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import ru.jetlabs.ts.ticketsservice.tables.Tickets

class TicketDao(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<TicketDao>(Tickets)

    var tourId by Tickets.tourId
    var userId by Tickets.userId
    var agencyId by Tickets.agencyId
    var tourCost by Tickets.tourCost
    var transportCost by Tickets.transportCost
    var startDate by Tickets.startDate
    var endDate by Tickets.endDate
    var createdAt by Tickets.createdAt
    var status by Tickets.status

    val additionalUsers by AdditionalUserDao referrersOn Tickets.id
}