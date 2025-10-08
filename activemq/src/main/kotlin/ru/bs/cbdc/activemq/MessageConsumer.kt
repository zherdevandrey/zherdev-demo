package ru.bs.cbdc.activemq

import org.springframework.jms.annotation.JmsListener
import org.springframework.stereotype.Service

@Service
class MessageConsumer {

    @JmsListener(destination = "test")
    fun receiveMessage(message: String) {
        println("Received message: $message")
        // Process your message here
    }

}