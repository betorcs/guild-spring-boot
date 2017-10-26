package org.devmaster.guild.repository

import org.devmaster.guild.model.Product
import org.springframework.data.repository.CrudRepository


interface ProductRepository : CrudRepository<Product, Long>