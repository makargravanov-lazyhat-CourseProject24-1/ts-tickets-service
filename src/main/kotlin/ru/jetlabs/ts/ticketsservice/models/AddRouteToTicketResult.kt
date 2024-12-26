package ru.jetlabs.ts.ticketsservice.models


sealed interface AddRouteToTicketResult {
    data object Success : AddRouteToTicketResult
    sealed interface Error : AddRouteToTicketResult {
        val message: String

        data class TicketNotFound(val id: Long) : Error {
            override val message: String = "Ticket with id = $id not found"
        }

        data class UnknownError(override val message: String) : Error
    }
}