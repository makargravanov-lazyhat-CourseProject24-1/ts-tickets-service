package ru.jetlabs.ts.ticketsservice.daos

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import ru.jetlabs.ts.ticketsservice.tables.Tickets

class TicketDao(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<TicketDao>(Tickets)

    var tourId by Tickets.tourId
    var userId by Tickets.userId
    var tourCost by Tickets.tourCost
    var transportCost by Tickets.transportCost
    var createdAt by Tickets.createdAt

    val additionalUsers by AdditionalUserDao referrersOn Tickets.id
}