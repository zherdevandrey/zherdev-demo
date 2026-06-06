package ru.bs.cbdc.business.config

import io.temporal.client.WorkflowClient
import io.temporal.serviceclient.WorkflowServiceStubs
import io.temporal.worker.Worker
import io.temporal.worker.WorkerFactory
import jakarta.annotation.PostConstruct
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.bs.cbdc.business.activities.PaymentActivitiesImpl


@Configuration
class TemporalConfig {

    @Bean
    fun workerFactory(serviceStubs: WorkflowServiceStubs): WorkerFactory {
        val client: WorkflowClient = WorkflowClient.newInstance(serviceStubs)
        val factory: WorkerFactory = WorkerFactory.newInstance(client)
        val worker: Worker = factory.newWorker("TRAVEL_TASK_QUEUE")
        worker.registerActivitiesImplementations(PaymentActivitiesImpl())
        return factory
    }

    @Bean
    fun serviceStubs(): WorkflowServiceStubs {
        return WorkflowServiceStubs.newInstance()
    }

    @PostConstruct
    fun startWorker() {
        workerFactory(serviceStubs()).start()
    }
}
