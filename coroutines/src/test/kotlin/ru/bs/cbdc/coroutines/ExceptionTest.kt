package ru.bs.cbdc.coroutines

import kotlinx.coroutines.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.concurrent.Executors
import kotlin.coroutines.EmptyCoroutineContext


class ExceptionTest {

    @Test
    fun childExceptionCauseParentCancellation() = runBlocking {
        val supervisorScope = coroutineScope {
            launch {
                repeat(10) { i ->
                    println("Задача A: $i")
                    delay(100)
                }
            }

            launch {
                repeat(3) { i ->
                    println("Задача B: $i")
                    delay(100)
                    if (i == 1) throw RuntimeException("Ошибка в задаче B")
                }
            }

        }
        supervisorScope.join()
    }

    /*
    * Ошибка в задаче Б не приводит к отмене задачи А
    * */
    @Test
    fun childException_DOES_NOT_CauseParentCancellation() = runBlocking {
        supervisorScope {
            val deferred1 = async {
                repeat(10) { i ->
                    println("Задача A: $i")
                    delay(100)
                }
                "Результат A"
            }

            val deferred2 = async {
                repeat(3) { i ->
                    println("Задача B: $i")
                    delay(100)
                    if (i == 1) {
                        println("Ошибка в задаче B")
                        throw RuntimeException("Ошибка в задаче B")
                    }
                }
                "Результат B"
            }

            try {
                println(deferred1.await() + deferred2.await())
            } catch (e: Exception) {
                println("EX ${e.message}")
            }
        }
    }

    @Test
            /*
        * Ошибка не поймана тк она распространяется через контекст. Исключение кинет coroutineScope
        * */
    fun invalidExceptionHandling() = runBlocking {
        coroutineScope {
            try {
                launch {
                    delay(1000)
                    throw RuntimeException("BOOM")
                }
            } catch (e: Exception) {
                println("Exception ${e.message}")
            }
        }
        println()
    }

    @Test
    fun validExceptionHandling() {
        runBlocking {
            coroutineScope {
                launch {
                    try {
                        delay(1000)
                        throw RuntimeException("BOOM")

                    } catch (e: Exception) {
                        println("Exception ${e.message}")
                    }
                }
            }
        }
    }

    @Test
    fun coroutineExceptionHandler() {
        runBlocking {
            val handler = CoroutineExceptionHandler { _, ex ->
                println("Exception ${ex.message}")
            }

            val scope = CoroutineScope(SupervisorJob() + handler)
            scope.launch {
                delay(200)
                println("RESULT_1")
            }.join()

            scope.launch {
                delay(100)
                throw Exception("MUST ME HANDLED IN CoroutineExceptionHandler")
            }.join()
        }
    }

    @Test
    /*
    * to fix use AtomicInteger
    * */
    fun sharedResourceInvalidCalculation() {
        var counter = 0

        val dispatcher = Executors.newFixedThreadPool(1000).asCoroutineDispatcher()

        runBlocking {
            withContext(dispatcher) {

                repeat(10_000) {
                    launch {
                        counter++
                    }
                }

            }
            delay(1000)
        }
        println("COUNTER = $counter")
    }

}