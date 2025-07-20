package ru.bs.cbdc.vaadin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class VaadinApplication

fun main(args: Array<String>) {
    runApplication<VaadinApplication>(*args)
}
