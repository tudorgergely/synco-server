package com.synco

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.scheduling.annotation.EnableAsync


@SpringBootApplication
@EnableAsync
open class ServerApplication

@Throws(InterruptedException::class)
fun main(args: Array<String>) {
    SpringApplication.run(ServerApplication::class.java, *args)
}
