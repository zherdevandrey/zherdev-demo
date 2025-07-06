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
class KafkaOptimizationApplication(val kafkaTemplate: KafkaTemplate<String, String>):CommandLineRunner {

    private val map = ConcurrentHashMap<String, Long>()
    private val counter = AtomicInteger(0)
    private val total = 100

    private var start = 0L


    @KafkaListener(topics = ["test-performance"], groupId = "test-performance")
    fun listen(record:ConsumerRecord<String, String>){
        val time = System.currentTimeMillis() - map[record.key()]!!

        map[record.key()] =  time
        Thread.sleep(100)

        val counterValue = counter.incrementAndGet()
        if (counterValue == total) {
            val average = map.values.sumOf { it } / map.values.size
            println("average=$average")
            println("time=${(System.currentTimeMillis()-start)}")
        }

    }

    override fun run(vararg args: String?): Unit = runBlocking {
        coroutineScope {
            val intRange = 1..total
            intRange.map {
                launch {
//                    kafkaTemplate.executeInTransaction {  }
                    val id = UUID.randomUUID().toString()
                    kafkaTemplate.send("test-performance", id, id )
                    map[id] =System.currentTimeMillis()
                }
            }
        }
        println("All published")
        start = System.currentTimeMillis()
    }

}

fun main(args: Array<String>) {
    runApplication<KafkaOptimizationApplication>(*args)
}



