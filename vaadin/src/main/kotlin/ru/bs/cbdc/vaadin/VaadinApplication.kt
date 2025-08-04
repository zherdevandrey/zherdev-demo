package ru.bs.cbdc.vaadin

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import ru.bs.cbdc.vaadin.entity.Company
import ru.bs.cbdc.vaadin.entity.Contact
import ru.bs.cbdc.vaadin.repository.CompanyRepository
import ru.bs.cbdc.vaadin.repository.ContactRepository

@SpringBootApplication
class VaadinApplication

fun main(args: Array<String>) {
    runApplication<VaadinApplication>(*args)
}
