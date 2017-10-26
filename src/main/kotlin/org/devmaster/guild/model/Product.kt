package org.devmaster.guild.model

import javax.persistence.*

@Entity
@Table(name = "products")
data class Product(

        var name: String,

        @Column(unique = true)
        var sku: String,

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long? = null)