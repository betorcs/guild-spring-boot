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
        var p = Product()
        p.name = "Mouse"
        p.sku = "M001"
        repository.save(p)

        p = Product()
        p.name = "Monitor"
        p.sku = "M002"
        repository.save(p)
    }

}

fun main(args: Array<String>) {
    SpringApplication.run(GuildApplication::class.java, *args)
}
