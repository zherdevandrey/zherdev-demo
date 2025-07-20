package com.example

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting2() {
    routing {
        get("/test") {
            call.respondText("Hello World2!")
        }
    }
}
