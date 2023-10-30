package com.example.demo

import org.springframework.web.bind.annotation.*

class DemoController {
    @RestController
    @RequestMapping("/api")
    class MyController {
        @GetMapping("/greet")
        fun greet(): String {
            return "Hello, World!"
        }

        @PostMapping("/greet")
        fun poss(@RequestBody body: DemoRequest): DemoResponse {
//            println(body)
            return DemoResponse("aaa")
        }
    }
}