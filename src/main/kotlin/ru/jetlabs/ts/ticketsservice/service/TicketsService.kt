package ru.jetlabs.ts.ticketsservice.service

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import ru.jetlabs.ts.ticketsservice.client.tourdata.HotelRoomsClient
import ru.jetlabs.ts.ticketsservice.client.tourdata.HotelsNutritionsClient
import ru.jetlabs.ts.ticketsservice.daos.AdditionalUserDao
import ru.jetlabs.ts.ticketsservice.daos.TicketDao
import ru.jetlabs.ts.ticketsservice.daos.TicketRouteBindingDao
import ru.jetlabs.ts.ticketsservice.daos.TicketStatusLogDao
import ru.jetlabs.ts.ticketsservice.models.*
import java.sql.SQLException

@Component
@Transactional
class TicketsService(
    private val hotelRoomsClient: HotelRoomsClient,
    private val hotelsNutritionsClient: HotelsNutritionsClient
) {
    fun registerTicket(form: RegisterTicketForm): RegisterTicketResult {
        try {
            val room = hotelRoomsClient.getRoomById(form.tour.roomId).body ?: return RegisterTicketResult.RoomNotFound
            val nutrition = hotelsNutritionsClient.getNutritionById(form.tour.nutritionId).body
                ?: return RegisterTicketResult.NutritionNotFound

            val costPerDay = room.costPerDay + nutrition.costPerDay

            val durationDays = form.tour.endDate.toEpochDay() - form.tour.startDate.toEpochDay()

            return TicketDao.new {
                userId = form.userId
                tourId = form.tour.id
                tourCost = durationDays * costPerDay
                transportCost = null
                startDate = form.tour.startDate
                endDate = form.tour.endDate
            }.also {
                TicketStatusLogDao.new {
                    ticket = it
                    status = TicketStatus.CREATED
                }
            }.mapToTicket().let {
                RegisterTicketResult.Success(it)
            }
        } catch (e: SQLException) {
            return RegisterTicketResult.UnknownError(e.stackTraceToString())
        }
    }

    fun addRouteToTicket(id: Long, form: AddRouteToTicketForm): AddRouteToTicketResult {
        try {
            val ticketDao = TicketDao.findById(id) ?: return AddRouteToTicketResult.Error.TicketNotFound(id)
            TicketRouteBindingDao.new {
                ticket = ticketDao
                routeId = form.routeId
            }
            return AddRouteToTicketResult.Success
        } catch (e: SQLException) {
            return AddRouteToTicketResult.Error.UnknownError(e.stackTraceToString())
        }
    }

    fun addUserToTicket(id: Long, form: AddUserToTicketForm): AddUserToTicketResult {
        try {
            val ticketDao = TicketDao.findById(id) ?: return AddUserToTicketResult.Error.TicketNotFound(id)
            AdditionalUserDao.new {
                ticket = ticketDao
                userId = form.userId
            }
            return AddUserToTicketResult.Success
        } catch (e: SQLException) {
            return AddUserToTicketResult.Error.UnknownError(message = e.stackTraceToString())
        }
    }

    fun requestTicketPayment(id: Long): RequestTicketPaymentResult {
        try {
            val ticketDao =
                TicketDao.findById(id) ?: return RequestTicketPaymentResult.Error.TicketNotFound(id = id)
            TicketStatusLogDao.new {
                ticket = ticketDao
                status = TicketStatus.PENDING
            }
            return RequestTicketPaymentResult.Success
        } catch (e: SQLException) {
            return RequestTicketPaymentResult.Error.UnknownError(e.stackTraceToString())
        }
    }

    fun cancelTicket(id: Long): CancelTicketResult {
        try {
            val ticketDao =
                TicketDao.findById(id) ?: return CancelTicketResult.Error.TicketNotFound(id = id)
            TicketStatusLogDao.new {
                ticket = ticketDao
                status = TicketStatus.CANCELLED
            }
            return CancelTicketResult.Success
        } catch (e: SQLException) {
            return CancelTicketResult.Error.UnknownError(message = e.stackTraceToString())
        }
    }

    fun approveTicket(id: Long): ApproveTicketResult {
        try {
            val ticketDao =
                TicketDao.findById(id) ?: return ApproveTicketResult.Error.TicketNotFound(id)
            TicketStatusLogDao.new {
                ticket = ticketDao
                status = TicketStatus.PAYED
            }
            return ApproveTicketResult.Success
        } catch (e: SQLException) {
            return ApproveTicketResult.Error.UnknownError(message = e.stackTraceToString())
        }
    }
}

