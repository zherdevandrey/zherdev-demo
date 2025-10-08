package com.example.kafkaoptimization

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicInteger



/*
* Идеи оптимизации
* Увеличение кол-ва партиций
*
* */

@SpringBootApplication
class KafkaOptimizationApplication {



    @KafkaListener(topics = ["test-performance"], groupId = "test-performance")
    fun listen(record:ConsumerRecord<String, String>){

    }


}

fun main(args: Array<String>) {
    runApplication<KafkaOptimizationApplication>(*args)
}



