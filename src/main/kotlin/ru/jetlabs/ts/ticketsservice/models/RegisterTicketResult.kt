package ru.jetlabs.ts.ticketsservice.models

sealed interface RegisterTicketResult {
    data class Success(val ticket: Ticket) : RegisterTicketResult
    data object RoomNotFound : RegisterTicketResult
    data object NutritionNotFound : RegisterTicketResult
    data class UnknownError(val message: String) : RegisterTicketResult
}