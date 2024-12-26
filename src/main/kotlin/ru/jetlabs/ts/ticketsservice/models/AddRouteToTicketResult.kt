package ru.jetlabs.ts.ticketsservice.models

sealed interface AddRouteToTicketResult {
    data object Success : AddRouteToTicketResult
    data class UnknownError(val message: String) : AddRouteToTicketResult
}