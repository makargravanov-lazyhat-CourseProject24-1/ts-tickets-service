package ru.jetlabs.ts.ticketsservice.db

import org.jetbrains.exposed.sql.SchemaUtils
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import ru.jetlabs.ts.ticketsservice.tables.AdditionalUsers
import ru.jetlabs.ts.ticketsservice.tables.TicketRouteBindings
import ru.jetlabs.ts.ticketsservice.tables.TicketStatusLogs
import ru.jetlabs.ts.ticketsservice.tables.Tickets

@Component
@Transactional
class SchemaInitialize : ApplicationRunner {

    override fun run(args: ApplicationArguments?) {
        SchemaUtils.create(Tickets, AdditionalUsers, TicketStatusLogs, TicketRouteBindings)
    }
}