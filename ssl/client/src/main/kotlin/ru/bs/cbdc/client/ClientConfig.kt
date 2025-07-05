package ru.bs.cbdc.client


import org.apache.hc.client5.http.impl.classic.HttpClients
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder
import org.apache.hc.client5.http.io.HttpClientConnectionManager
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactoryBuilder
import org.apache.hc.client5.http.ssl.TrustSelfSignedStrategy
import org.apache.hc.core5.ssl.SSLContextBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.Resource
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.web.client.RestTemplate
import java.security.KeyStore
import javax.net.ssl.SSLContext

@Configuration
class ClientConfig {
    @Value("classpath:client.jks")
    private lateinit var keyStore: Resource

    @Value("classpath:client-truststore.jks")
    private lateinit var trustStore: Resource


    @Bean
    fun restTemplate(): RestTemplate {
        // 1. Загружаем KeyStore и TrustStore
        val keyStore = KeyStore.getInstance("JKS")
        keyStore.load(ClassPathResource("client.jks").inputStream, "123456".toCharArray())

        val trustStore = KeyStore.getInstance("JKS")
        trustStore.load(ClassPathResource("client-truststore.jks").inputStream, "123456".toCharArray())

        // 2. Настраиваем SSLContext
        val sslContext: SSLContext = SSLContextBuilder.create()
            .loadKeyMaterial(keyStore, "123456".toCharArray()) // клиентский сертификат
            .loadTrustMaterial(trustStore, TrustSelfSignedStrategy()) // доверенные сертификаты
            .build()

        // 3. Создаём фабрику SSL-сокетов
        val sslSocketFactory: SSLConnectionSocketFactory = SSLConnectionSocketFactoryBuilder.create()
            .setSslContext(sslContext)
            .build()

        // 4. Настраиваем менеджер соединений
        val connectionManager: HttpClientConnectionManager = PoolingHttpClientConnectionManagerBuilder.create()
            .setSSLSocketFactory(sslSocketFactory)
            .build()

        // 5. Создаём HttpClient с SSL
        val httpClient = HttpClients.custom()
            .setConnectionManager(connectionManager)
            .evictExpiredConnections()
            .build()

        // 6. Связываем HttpClient с RestTemplate
        val requestFactory = HttpComponentsClientHttpRequestFactory(httpClient)
        return RestTemplate(requestFactory)
    }

}