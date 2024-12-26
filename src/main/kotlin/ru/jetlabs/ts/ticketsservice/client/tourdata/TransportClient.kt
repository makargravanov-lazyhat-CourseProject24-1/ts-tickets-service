package ru.jetlabs.ts.ticketsservice.client.tourdata

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import ru.jetlabs.ts.ticketsservice.client.tourdata.models.Transport
import ru.jetlabs.ts.ticketsservice.client.tourdata.models.enums.TransportType

@FeignClient(
    name = "ts-tour-data-service-transport",
    url = "http://ts-tour-data-service:8080/ts-tour-data-service/api/v1/transport"
)
interface TransportClient {
    @GetMapping
    fun getTransports(
        @RequestParam name: String?,
        @RequestParam type: TransportType?,
        @RequestParam contractorId: Long?
    ): ResponseEntity<List<Transport>>

    @GetMapping("/{id}")
    fun getHotelById(@PathVariable id: Long): ResponseEntity<Transport>
}