package org.devmaster.guild

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class GuildApplication

fun main(args: Array<String>) {
    SpringApplication.run(GuildApplication::class.java, *args)
}
