package ru.jetlabs.ts.ticketsservice.client.tourdata

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import ru.jetlabs.ts.ticketsservice.client.tourdata.models.HotelNutrition
import ru.jetlabs.ts.ticketsservice.client.tourdata.models.enums.NutritionType

@FeignClient(
    name = "ts-tour-data-service-nutritions",
    url = "http://ts-tour-data-service:8080/ts-tour-data-service/api/v1/nutritions"
)
interface  HotelsNutritionsController {
    @GetMapping
    fun getNutritions(
        @RequestParam type: NutritionType?, @RequestParam costPerDay: Double?
    ): ResponseEntity<List<HotelNutrition>>

    @GetMapping("/{id}")
    fun getNutritionById(@PathVariable id: Long): ResponseEntity<HotelNutrition>
}