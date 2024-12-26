package ru.jetlabs.ts.ticketsservice.client.tourdata

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import ru.jetlabs.ts.ticketsservice.client.tourdata.models.HotelRoom
import ru.jetlabs.ts.ticketsservice.client.tourdata.models.enums.RoomCapacity
import ru.jetlabs.ts.ticketsservice.client.tourdata.models.enums.RoomType

@FeignClient(
    name = "ts-tour-data-service-rooms",
    url = "http://ts-tour-data-service:8080/ts-tour-data-service/api/v1/rooms"
)
interface HotelRoomsClient {
    @GetMapping
    fun getRooms(
        @RequestParam hotelId: Long?,
        @RequestParam capacity: RoomCapacity?,
        @RequestParam type: RoomType?,
        @RequestParam wifi: Boolean?
    ): ResponseEntity<List<HotelRoom>>

    @GetMapping("/{id}")
    fun getRoomById(@PathVariable id: Long): ResponseEntity<HotelRoom>
}

