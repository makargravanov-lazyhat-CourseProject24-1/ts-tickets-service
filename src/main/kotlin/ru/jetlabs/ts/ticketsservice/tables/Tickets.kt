package ru.jetlabs.ts.ticketsservice.tables

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object Tickets : LongIdTable("tickets") {
    val tourId = long("tour_id")
    val userId = long("user_id")
    val tourCost = double("tour_cost")
    val transportCost = double("transport_cost").nullable()
    val createdAt = datetime("created_at").clientDefault { LocalDateTime.now() }
}