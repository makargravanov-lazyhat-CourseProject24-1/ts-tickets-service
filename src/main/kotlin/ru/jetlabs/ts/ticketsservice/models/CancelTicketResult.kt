package ru.jetlabs.ts.ticketsservice.models

sealed interface CancelTicketResult {
    data object Success : CancelTicketResult
    data class UnknownError(val message: String) : CancelTicketResult
}

sealed interface ApproveTicketResult {
    data object Success : ApproveTicketResult
    data class UnknownError(val message: String) : ApproveTicketResult
}