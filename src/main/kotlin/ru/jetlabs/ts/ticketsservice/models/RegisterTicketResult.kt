package ru.jetlabs.ts.ticketsservice.models

sealed interface RegisterTicketResult {
    data class Success(val ticket: Ticket) : RegisterTicketResult
    data class UnknownError(val message: String) : RegisterTicketResult
}