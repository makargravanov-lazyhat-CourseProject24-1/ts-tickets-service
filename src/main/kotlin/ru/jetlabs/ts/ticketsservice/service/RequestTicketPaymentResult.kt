package ru.jetlabs.ts.ticketsservice.service

sealed interface RequestTicketPaymentResult {
    data object Success : RequestTicketPaymentResult
    sealed interface Error : RequestTicketPaymentResult {
        val message: String

        data class TicketNotFound(val id: Long) : Error {
            override val message: String = "Ticket with id = $id not found"
        }

        data class UnknownError(override val message: String) : Error
    }
}