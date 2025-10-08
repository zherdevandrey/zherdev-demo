package ru.bs.cbdc.activemq

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jms.annotation.EnableJms
import org.springframework.jms.config.DefaultJmsListenerContainerFactory
import org.springframework.jms.core.JmsTemplate
import org.springframework.jms.support.converter.MappingJackson2MessageConverter
import org.springframework.jms.support.converter.MessageConverter
import org.springframework.jms.support.converter.MessageType

@Configuration
@EnableJms
class ActiveMQConfig {

    @Value("\${spring.activemq.broker-url}")
    private lateinit var brokerUrl: String

    @Value("\${spring.activemq.user}")
    private lateinit var username: String

    @Value("\${spring.activemq.password}")
    private lateinit var password: String

    @Bean
    fun connectionFactory(): ActiveMQConnectionFactory {
        val connectionFactory = ActiveMQConnectionFactory()
        connectionFactory.setBrokerURL(brokerUrl)
        connectionFactory.setUser(username)
        connectionFactory.setPassword(password)
        return connectionFactory
    }

    @Bean
    fun jmsTemplate(): JmsTemplate {
        val template = JmsTemplate(connectionFactory())
        template.messageConverter = jacksonJmsMessageConverter()
        return template
    }

    @Bean
    fun jmsListenerContainerFactory(): DefaultJmsListenerContainerFactory {
        val factory = DefaultJmsListenerContainerFactory()
        factory.setConnectionFactory(connectionFactory())
        factory.setMessageConverter(jacksonJmsMessageConverter())
        factory.setConcurrency("1-1")
        return factory
    }

    @Bean
    fun jacksonJmsMessageConverter(): MessageConverter {
        val converter = MappingJackson2MessageConverter()
        converter.setTargetType(MessageType.TEXT)
        converter.setTypeIdPropertyName("_type")
        return converter
    }
}