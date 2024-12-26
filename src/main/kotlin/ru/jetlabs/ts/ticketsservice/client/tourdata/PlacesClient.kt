package ru.jetlabs.ts.ticketsservice.client.tourdata

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(
    name = "ts-tour-data-service-places",
    url = "http://ts-tour-data-service:8080/ts-tour-data-service/api/v1/places"
)
interface PlacesClient {
    @GetMapping
    fun getPlaces(@RequestParam name: String?, @RequestParam address: String?): ResponseEntity<*>
}