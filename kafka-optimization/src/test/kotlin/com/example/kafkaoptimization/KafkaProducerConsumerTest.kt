package com.example.kafkaoptimization

import org.springframework.kafka.test.context.EmbeddedKafka
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@EmbeddedKafka(
    partitions = 1,
    brokerProperties = [
        "listeners=PLAINTEXT://localhost:9092",  // Фиксируем порт для внешнего подключения
        "port=9092",
        "auto.create.topics.enable=true"  // Автоматическое создание топиков
    ]
)
class KafkaProducerConsumerTest {

    @Test
    fun `test send and receive message`() {

    }
}