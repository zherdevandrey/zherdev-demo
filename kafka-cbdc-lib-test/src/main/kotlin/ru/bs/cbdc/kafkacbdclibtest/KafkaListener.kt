package ru.bs.cbdc.kafkacbdclibtest

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.messaging.MessageHeaders
import org.springframework.stereotype.Component
import ru.bs.cbdc.kafka.TopicKafkaListener

@Component
class KafkaListener {

    @TopicKafkaListener(id = "cc-gw-receive", topic = "")
    fun listen(headers: MessageHeaders, record: ConsumerRecord<out Any?, out Any?>) {
        println("")
    }

}