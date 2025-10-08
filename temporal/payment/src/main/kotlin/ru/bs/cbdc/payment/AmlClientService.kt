package ru.bs.cbdc.payment


import AmlCftResponseKt
import AmlServiceGrpcKt
import net.devh.boot.grpc.client.inject.GrpcClient
import org.springframework.stereotype.Service

@Service
class AmlClientService {

    @GrpcClient("aml-service")
    private lateinit var amlServiceClient: AmlServiceGrpcKt.AmlServiceCoroutineStub

    suspend fun checkAml(oprId: String): Aml.AmlCftResponse {
        val request = Aml.AmlCftRequest.newBuilder().setOprId(oprId).build()
        return amlServiceClient.checkAml(request)
    }

}