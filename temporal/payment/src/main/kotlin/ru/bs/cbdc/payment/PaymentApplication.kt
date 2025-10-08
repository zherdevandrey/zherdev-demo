package ru.bs.cbdc.payment

import kotlinx.coroutines.runBlocking
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.util.UUID
import kotlin.coroutines.CoroutineContext

@SpringBootApplication
class PaymentApplication(val amlClientService:AmlClientService): CommandLineRunner {
    override fun run(vararg args: String?): Unit = runBlocking {
        amlClientService.checkAml(UUID.randomUUID().toString())
    }

}

fun main(args: Array<String>) {
    runApplication<PaymentApplication>(*args)
}
