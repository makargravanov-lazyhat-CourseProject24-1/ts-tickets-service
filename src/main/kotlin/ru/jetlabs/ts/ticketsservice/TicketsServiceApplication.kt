package ru.jetlabs.ts.ticketsservice

import org.jetbrains.exposed.spring.autoconfigure.ExposedAutoConfiguration
import org.springframework.boot.autoconfigure.ImportAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
@ImportAutoConfiguration(ExposedAutoConfiguration::class)
class TicketsServiceApplication

fun main(args: Array<String>) {
    runApplication<TicketsServiceApplication>(*args)
}

