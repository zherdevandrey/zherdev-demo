package ru.bs.cbdc.client

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.client.RestTemplate

@SpringBootApplication
class ClientApplication(val restTemplate: RestTemplate): CommandLineRunner {
    override fun run(vararg args: String?) {
        val result = restTemplate.getForEntity("https://ssl-domain:8443", String::class.java)
//        val result = restTemplate.getForEntity("https://localhost:8443", String::class.java)
        println(result.body)
    }

}

fun main(args: Array<String>) {
    runApplication<ClientApplication>(*args)
}
