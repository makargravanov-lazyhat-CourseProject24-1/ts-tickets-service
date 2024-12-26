package ru.jetlabs.ts.ticketsservice.client.tourdata.models

import ru.jetlabs.ts.tourdataservice.models.enums.TransportType

data class Transport(
    val id: Long,
    val type: TransportType,
    val name: String,
    val contractor: TransportContractor,
)