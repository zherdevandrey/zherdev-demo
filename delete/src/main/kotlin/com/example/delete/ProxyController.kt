package com.example.delete

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ProxyController {

    @GetMapping("/test")
    fun test():String {
        return ""
    }

}

