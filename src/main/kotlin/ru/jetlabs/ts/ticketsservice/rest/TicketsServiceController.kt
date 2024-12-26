package ru.jetlabs.ts.ticketsservice.rest

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.jetlabs.ts.ticketsservice.models.*
import ru.jetlabs.ts.ticketsservice.service.RequestTicketPaymentResult
import ru.jetlabs.ts.ticketsservice.service.TicketsService

@RestController
@RequestMapping("/ts-tickets-service/api/v1/tickets")
class TicketsServiceController(
    val ticketsService: TicketsService
) {
    @PostMapping
    fun registerTicket(form: RegisterTicketForm): ResponseEntity<*> = ticketsService.registerTicket(form).let {
        when (it) {
            is RegisterTicketResult.Success -> ResponseEntity.status(HttpStatus.OK).body(it.ticket)
            is RegisterTicketResult.UnknownError -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(it)
        }
    }

    @PostMapping("/{id}/addroute")
    fun addRouteToTicket(@PathVariable id: Long, @RequestBody body: AddRouteToTicketForm): ResponseEntity<*> =
        ticketsService.addRouteToTicket(
            id = id,
            form = body
        ).let {
            when (it) {
                is AddRouteToTicketResult.Success -> ResponseEntity.status(HttpStatus.OK).build()
                is AddRouteToTicketResult.Error -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(it)
            }
        }

    @PostMapping("/{id}/adduser")
    fun addUserToTicket(@PathVariable id: Long, @RequestBody body: AddUserToTicketForm): ResponseEntity<*> =
        ticketsService.addUserToTicket(id = id, form = body).let {
            when (it) {
                is AddUserToTicketResult.Success -> ResponseEntity.status(HttpStatus.OK).build()
                is AddUserToTicketResult.Error -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(it)
            }
        }

    @GetMapping("/{id}/request-pay")
    fun requestTicketPayment(@PathVariable id: Long): ResponseEntity<*> =
        ticketsService.requestTicketPayment(id = id).let {
            when (it) {
                is RequestTicketPaymentResult.Success -> ResponseEntity.status(HttpStatus.OK).build()
                is RequestTicketPaymentResult.Error -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(it)
            }
        }

    @PutMapping("/{id}/cancel")
    fun cancelTicket(@PathVariable id: Long): ResponseEntity<*> = ticketsService.cancelTicket(id = id).let {
        when (it) {
            is CancelTicketResult.Success -> ResponseEntity.status(HttpStatus.OK).build()
            is CancelTicketResult.Error -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(it)
        }
    }

    @PutMapping("/{id}/approve")
    fun approveTicket(@PathVariable id: Long): ResponseEntity<*> = ticketsService.approveTicket(id = id).let {
        when (it) {
            is ApproveTicketResult.Success -> ResponseEntity.status(HttpStatus.OK).build()
            is ApproveTicketResult.Error -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(it)
        }
    }
}

