package org.devmaster.guild

import org.devmaster.guild.model.Product
import org.devmaster.guild.repository.ProductRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class GuildApplication {

    @Bean
    fun createSomeProducts(repository: ProductRepository) = CommandLineRunner {
        repository.save(Product("Mouse", "M001"))
        repository.save(Product("Monitor", "M002"))
    }

}

fun main(args: Array<String>) {
    SpringApplication.run(GuildApplication::class.java, *args)
}
