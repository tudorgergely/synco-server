package com.synco

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication


@SpringBootApplication
open class ServerApplication

@Throws(InterruptedException::class)
fun main(args: Array<String>) {
    SpringApplication.run(ServerApplication::class.java, *args)
}
