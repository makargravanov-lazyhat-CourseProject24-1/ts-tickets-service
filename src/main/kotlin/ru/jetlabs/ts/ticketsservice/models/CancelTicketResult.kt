package ru.jetlabs.ts.ticketsservice.models

sealed interface CancelTicketResult {
    data object Success : CancelTicketResult
    sealed interface Error : CancelTicketResult {
        val message: String

        data class TicketNotFound(val id: Long) : Error {
            override val message: String = "Ticket with id = $id not found"
        }

        data class UnknownError(override val message: String) : Error
    }
}

