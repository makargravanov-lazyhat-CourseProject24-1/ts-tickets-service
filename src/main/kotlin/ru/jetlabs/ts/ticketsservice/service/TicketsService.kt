package ru.jetlabs.ts.ticketsservice.service

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import ru.jetlabs.ts.ticketsservice.daos.AdditionalUserDao
import ru.jetlabs.ts.ticketsservice.daos.TicketDao
import ru.jetlabs.ts.ticketsservice.daos.TicketStatusLogDao
import ru.jetlabs.ts.ticketsservice.models.*
import java.sql.SQLException

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

    fun addUserToTicket(ticket: Long, form: AddUserToTicketForm): AddUserToTicketResult = try {
        AdditionalUserDao.new {
            ticketId._value = ticket
            userId = form.userId
        }
        TicketDao.findById(ticket)?.mapToTicket()?.let {
            AddUserToTicketResult.Success
        } ?: AddUserToTicketResult.Error.TicketNotFound(ticket)
    } catch (e: SQLException) {
        AddUserToTicketResult.Error.UnknownError(message = e.stackTraceToString())
    }

    fun cancelTicket(id: Long): CancelTicketResult = try {
        TicketStatusLogDao.new {
            ticketId._value = id
            status = TicketStatus.CANCELLED
        }
        CancelTicketResult.Success
    } catch (e: SQLException) {
        CancelTicketResult.UnknownError(message = e.stackTraceToString())
    }

    fun approveTicket(id: Long): ApproveTicketResult = try {
        TicketStatusLogDao.new {
            ticketId._value = id
            status = TicketStatus.PAYED
        }
        ApproveTicketResult.Success
    } catch (e: SQLException) {
        ApproveTicketResult.UnknownError(message = e.stackTraceToString())
    }
}