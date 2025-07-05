package com.example.temporal

import io.temporal.activity.ActivityInterface
import io.temporal.activity.ActivityMethod
import org.springframework.stereotype.Component

@ActivityInterface
interface TemporalActivity {

    @ActivityMethod
    fun start(orderId: String)

    @ActivityMethod
    fun proceed(orderId: String)

}

@Component
class TemporalActivityImpl : TemporalActivity {
    override fun start(orderId: String) {
        println("START: $orderId")
    }


    override fun proceed(orderId: String) {
        println("PROCEED: $orderId")
    }
}