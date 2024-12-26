package ru.jetlabs.ts.ticketsservice.models

sealed interface RequestTicketPaymentResult {
    data class Success(val result: String) : RequestTicketPaymentResult
    sealed interface Error : RequestTicketPaymentResult {
        val message: String

        data class TicketNotFound(val id: Long) : Error {
            override val message: String = "Ticket with id = $id not found"
        }

        data class UnknownError(override val message: String) : Error
    }
}