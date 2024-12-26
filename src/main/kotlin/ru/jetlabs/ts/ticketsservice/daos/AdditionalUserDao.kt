package ru.jetlabs.ts.ticketsservice.daos

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import ru.jetlabs.ts.ticketsservice.tables.AdditionalUsers

class AdditionalUserDao(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<AdditionalUserDao>(AdditionalUsers)

    var ticketId by AdditionalUsers.ticketId
    var userId by AdditionalUsers.userId
}