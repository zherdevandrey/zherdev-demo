package ru.bs.cbdc.concurrent.jpa

import jakarta.persistence.criteria.Predicate
import jakarta.transaction.Transactional
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import ru.bs.cbdc.concurrent.jpa.entity.ConcurrentEntity
import ru.bs.cbdc.concurrent.jpa.service.AbstractConcurrentService
import java.util.function.Consumer

@SpringBootApplication
class ConcurrentJpaApplication():CommandLineRunner {

    override fun run(vararg args: String?) {

    }

}


fun main(args: Array<String>) {
    runApplication<ConcurrentJpaApplication>(*args)
}
