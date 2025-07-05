package ru.bs.cbdc.server

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller {

    @GetMapping("/")
    fun server(): String {
        return "HELLO FROM SSL SERVER"
    }

}