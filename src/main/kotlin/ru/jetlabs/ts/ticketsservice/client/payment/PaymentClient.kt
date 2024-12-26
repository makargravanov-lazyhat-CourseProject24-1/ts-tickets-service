package ru.jetlabs.ts.ticketsservice.client.payment

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(name = "ts-payment-service", url = "http://ts-payment-service:8080/ts-payment-service/api/v1")
interface PaymentClient {
    @PostMapping("/bind-transaction-status")
    fun registerTransaction(@RequestBody form: TransactionRegisterRequestForm): ResponseEntity<*>
}

