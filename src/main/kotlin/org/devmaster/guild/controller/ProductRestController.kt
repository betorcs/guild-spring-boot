package org.devmaster.guild.controller

import org.devmaster.guild.model.Product
import org.devmaster.guild.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
class ProductRestController {

    @Autowired
    lateinit var repository: ProductRepository

    @PostMapping(path = arrayOf("/product"))
    fun save(@Valid @RequestBody product: Product): Product {
        return repository.save(product)
    }

    @DeleteMapping(path = arrayOf("/product/{productId}"))
    fun delete(@PathVariable productId: Long) {
        repository.delete(productId)
    }

    @GetMapping(path = arrayOf("/product/{sku}"))
    fun findBySku(@PathVariable sku: String): Product? {
        return repository.findOneBySku(sku)
    }

    @GetMapping(path = arrayOf("/products"))
    fun findAll(): MutableIterable<Product>? {
        return repository.findAll()
    }

}