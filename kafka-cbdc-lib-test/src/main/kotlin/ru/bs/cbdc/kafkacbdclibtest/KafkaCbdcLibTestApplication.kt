package ru.bs.cbdc.kafkacbdclibtest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KafkaCbdcLibTestApplication

fun main(args: Array<String>) {
	runApplication<KafkaCbdcLibTestApplication>(*args)
}
