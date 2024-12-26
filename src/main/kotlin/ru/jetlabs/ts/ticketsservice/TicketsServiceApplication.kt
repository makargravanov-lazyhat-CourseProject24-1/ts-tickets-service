package ru.jetlabs.ts.ticketsservice

import org.jetbrains.exposed.dao.id.LongIdTable
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import ru.jetlabs.ts.ticketsservice.tables.Tickets

@SpringBootApplication
class TicketsServiceApplication

fun main(args: Array<String>) {
    runApplication<TicketsServiceApplication>(*args)
}

object TicketRoutesBindings : LongIdTable("ticket_routes_bindings") {
    val ticket = reference("ticket", Tickets)

}

