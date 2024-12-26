package ru.jetlabs.ts.ticketsservice

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.javatime.datetime
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import java.sql.SQLException
import java.time.LocalDateTime

@SpringBootApplication
class TicketsServiceApplication

fun main(args: Array<String>) {
    runApplication<TicketsServiceApplication>(*args)
}

@RestController
@RequestMapping("/ts-tickets-service/api/v1")
class TicketsServiceController(
    val ticketsService: TicketsService
) {
    @PostMapping("/register")
    fun registerTicket(form: RegisterTicketForm): ResponseEntity<*> = ticketsService.registerTicket(form).let {
        when (it) {
            is RegisterTicketResult.Success -> ResponseEntity.status(HttpStatus.OK).body(it.ticket)
            is RegisterTicketResult.UnknownError -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(it)
        }
    }

    @GetMapping("/addroute")
    fun addRouteToTicket(@RequestParam routeId: Long): ResponseEntity<*>

    @GetMapping("/adduser")
    fun addUserToTicket(@RequestBody body: AddUserToTicketForm): ResponseEntity<*> =
        ticketsService.addUserToTicket(form = body).let {
            when (it) {
                is AddUserToTicketResult.Success -> ResponseEntity.status(HttpStatus.OK).build()
                is AddUserToTicketResult.Error -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(it)
            }
        }
}

@Component
@Transactional
class TicketsService {
    fun registerTicket(form: RegisterTicketForm): RegisterTicketResult =
        try {
            TicketDao.new {
                tourId = form.tourId
                userId = form.userId
            }.mapToTicket().let {
                RegisterTicketResult.Success(it)
            }
        } catch (e: SQLException) {
            RegisterTicketResult.UnknownError(e.stackTraceToString())
        }

    fun addUserToTicket(form: AddUserToTicketForm): AddUserToTicketResult = try {
        AdditionalUserDao.new {
            ticketId._value = form.ticketId
            userId = form.userId
        }
        TicketDao.findById(form.ticketId)?.mapToTicket()?.let {
            AddUserToTicketResult.Success
        } ?: AddUserToTicketResult.Error.TicketNotFound(form.ticketId)
    } catch (e: SQLException) {
        AddUserToTicketResult.Error.UnknownError(message = e.stackTraceToString())
    }
}

data class AddUserToTicketForm(val ticketId: Long, val userId: Long)

sealed interface AddUserToTicketResult {
    data object Success : AddUserToTicketResult
    sealed interface Error : AddUserToTicketResult {
        val message: String

        data class TicketNotFound(val id: Long) : Error {
            override val message: String = "Ticket with id = $id not found"
        }

        data class UnknownError(override val message: String) : Error
    }
}

sealed interface AddRouteToTicketResult {
    data object Success : AddRouteToTicketResult
    data class UnknownError(val message: String) : AddRouteToTicketResult
}

sealed interface RegisterTicketResult {
    data class Success(val ticket: Ticket) : RegisterTicketResult
    data class UnknownError(val message: String) : RegisterTicketResult
}

object Tickets : LongIdTable("tickets") {
    val tourId = long("tour_id")
    val userId = long("user_id")
    val cost = float("cost")
    val createdAt = datetime("created_at").clientDefault { LocalDateTime.now() }
}

class TicketDao(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<TicketDao>(Tickets)

    var tourId by Tickets.tourId
    var userId by Tickets.userId
    var createdAt by Tickets.createdAt

    val additionalUsers by AdditionalUserDao referrersOn Tickets.id
}

enum class TicketStatus {
    CREATED, PAYED, CANCELLED
}

object TicketStatusLogs : LongIdTable("ticket_status_logs") {
    val ticketId = reference("ticket_id", Tickets)
    val status = enumeration<TicketStatus>("status")
    val createdAt = datetime("created_at").clientDefault { LocalDateTime.now() }
}

class TicketStatusLogDao(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<TicketStatusLogDao>(TicketStatusLogs)

    var ticketId by TicketStatusLogs.ticketId
    var status by TicketStatusLogs.status
    var createdAt by TicketStatusLogs.createdAt
}

data class TicketStatusLog(
    val id: Long,
    val ticketId: Long,
    val status: TicketStatus,
    val createdAt: LocalDateTime,
)

object AdditionalUsers : LongIdTable("additional_users") {
    val ticketId = reference("ticket_id", Tickets)
    val userId = long("user_id")
}

class AdditionalUserDao(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<AdditionalUserDao>(AdditionalUsers)

    var ticketId by AdditionalUsers.ticketId
    var userId by AdditionalUsers.userId
}

data class RegisterTicketForm(
    val tourId: Long,
    val cost: Float,
    val userId: Long
)

data class Ticket(
    val id: Long,
    val tourId: Long,
    val userId: Long,
    val additionalUsers: List<Long>
)

fun TicketDao.mapToTicket(): Ticket = Ticket(
    id = id.value,
    tourId = tourId,
    userId = userId,
    additionalUsers = additionalUsers.map { userId }.toList()
)