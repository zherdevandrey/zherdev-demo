package ru.bs.cbdc.coroutines

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

class ChanelTest {


    /*
    * Channel сущность для обмена данными между корутинами
    * Горячий источник данных. Публикация начнется в момент создания
    * */
    @Test
    fun testChanel() {
        runBlocking {
            val chanel = Channel<Int>()
            launch {
                repeat(10) {
                    println("publish $it")
                    chanel.send(it)
                    delay(50)
                }
            }
            launch {
                for(message in chanel) {
                    println("receive $message")
                }
            }
        }
    }

    /*
    * Публикация начнется в момент подписки
    * */
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun otherChanelExample() {
        runBlocking {
            val chanel = produce {
                repeat(10) {
                    send(it)
                }
            }

            launch {
                for(message in chanel) {
                    println("receive $message")
                }
            }
        }
    }


    @Test
    fun flowExample() = runBlocking{

        val flow = flow {
            repeat(10) {
                emit(it)
            }
        }

        delay(1000)

        flow.collect {
            println(it)
        }
    }

}