package ru.bs.cbdc.other

import amlCftResponse
import io.grpc.stub.StreamObserver
import net.devh.boot.grpc.server.service.GrpcService
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class OtherApplication

fun main(args: Array<String>) {
    runApplication<OtherApplication>(*args)
}

@GrpcService
class AmlService:AmlServiceGrpcKt.AmlServiceCoroutineImplBase() {

    override suspend fun checkAml(request: Aml.AmlCftRequest): Aml.AmlCftResponse {
        return amlCftResponse {
            amlStatus = Aml.AmlStatus.PASSED
        }
    }
}