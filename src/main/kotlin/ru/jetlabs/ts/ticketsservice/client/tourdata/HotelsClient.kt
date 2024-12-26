package ru.jetlabs.ts.ticketsservice.client.tourdata

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import ru.jetlabs.ts.ticketsservice.client.tourdata.models.Hotel
import ru.jetlabs.ts.ticketsservice.client.tourdata.models.enums.HotelLevel

@FeignClient(
    name = "ts-tour-data-service-hotels",
    url = "http://ts-tour-data-service:8080/ts-tour-data-service/api/v1/hotels"
)
interface HotelsClient {
    @GetMapping
    fun getHotels(
        @RequestParam level: HotelLevel?, @RequestParam placeId: Long?, @RequestParam name: String?
    ): ResponseEntity<List<Hotel>>

    @GetMapping("/{id}")
    fun getHotelById(@PathVariable id: Long): ResponseEntity<Hotel>
}