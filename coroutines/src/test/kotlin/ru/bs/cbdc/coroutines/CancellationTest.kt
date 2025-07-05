package ru.bs.cbdc.coroutines

import kotlinx.coroutines.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.coroutines.EmptyCoroutineContext


class CancellationTest {

    @Test
    fun cancelChildCoroutineDoesAffectParent() = runBlocking {
        val scope = CoroutineScope(EmptyCoroutineContext)

        val job1 = scope.launch {
            delay(500)
            println("FINISH 1")
        }

        val job2 = scope.launch {
            delay(500)
            println("FINISH 2")
        }

        val job3 = scope.launch {
            delay(1000)
            println("FINISH 3")
        }

        job1.join()
        job2.join()
        job3.cancel()

        assertFalse(job1.isCancelled)
        assertFalse(job2.isCancelled)
        assertTrue(job3.isCancelled)
    }

    @Test
    fun cancelParentWillCancelAllChild() = runBlocking {
        val scope = CoroutineScope(EmptyCoroutineContext)

        val job1 = scope.launch {
            delay(500)
            println("FINISH 1")
        }

        val job2 = scope.launch {
            delay(500)
            println("FINISH 2")
        }

        val job3 = scope.launch {
            delay(1000)
            println("FINISH 3")
        }
        scope.cancel()

        assertTrue(job1.isCancelled)
        assertTrue(job2.isCancelled)
        assertTrue(job3.isCancelled)
    }

}