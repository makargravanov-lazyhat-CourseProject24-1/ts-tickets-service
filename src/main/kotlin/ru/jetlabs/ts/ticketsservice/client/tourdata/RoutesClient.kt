package ru.jetlabs.ts.ticketsservice.client.tourdata

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import ru.jetlabs.ts.tourdataservice.models.enums.TransportType

@FeignClient(
    name = "ts-tour-data-service-routes",
    url = "http://ts-tour-data-service:8080/ts-tour-data-service/api/v1/routes"
)
interface RoutesClient {
    @GetMapping
    fun getRoutes(
        @RequestParam name: String?,
        @RequestParam departurePlaceId: Long?,
        @RequestParam arrivePlaceId: Long?,
        @RequestParam transportType: TransportType?,
    ): ResponseEntity<*>
}