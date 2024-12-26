package ru.jetlabs.ts.ticketsservice.client.payment

data class TransactionRegisterRequestForm(
    val amount: Float,
    val agencyId: Long,
    val ticketId: Long,
    val userId: Long
)