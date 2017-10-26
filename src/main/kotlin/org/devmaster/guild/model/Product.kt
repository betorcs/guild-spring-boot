package org.devmaster.guild.model

import javax.persistence.*

@Entity @Table(name = "products")
class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null
    var name: String? = null

    @Column(unique = true)
    var sku: String? = null
}