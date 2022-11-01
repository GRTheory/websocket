package com.example

import com.example.plugins.configureRouting
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {
    @Test
    fun testRoot() = testApplication {
        application {
            configureRouting()
        }
        client.get("/").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("Hello World!", bodyAsText())
        }
    }

    private fun getData(): String {
        Thread.sleep(3000)
        return "Flow test"
    }

    @Test
    fun testFlowNormal() {
        GlobalScope.launch {
            flow {
                emit(getData())
            }.flowOn(Dispatchers.IO)
                .onStart {
                    println("onStart")
                }.catch {
                    println("catch:${it.message}")
                }.onCompletion {
                    println("onComplete:${it?.message}")
                }.collect {
                    println("result = $it")
                }
        }
        Thread.sleep(6000)
    }
}