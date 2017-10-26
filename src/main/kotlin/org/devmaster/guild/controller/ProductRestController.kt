package org.devmaster.guild.controller

import org.devmaster.guild.model.Product
import org.devmaster.guild.repository.ProductRepository
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
class ProductRestController(var repository: ProductRepository) {

    @PostMapping("/product")
    fun save(@Valid @RequestBody product: Product) = repository.save(product)

    @DeleteMapping("/product/{productId}")
    fun delete(@PathVariable productId: Long) = repository.delete(productId)

    @GetMapping("/product/{sku}")
    fun findBySku(@PathVariable sku: String) = repository.findOneBySku(sku)

    @GetMapping("/products")
    fun findAll() = repository.findAll()

}