package org.devmaster.guild.repository

import org.devmaster.guild.model.Product
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory

@RunWith(SpringRunner::class)
@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    lateinit var emf: EntityManagerFactory

    @Autowired
    lateinit var repository: ProductRepository

    private val em: EntityManager by lazy {
        emf.createEntityManager()
    }

    @Test
    fun shouldPersistProductSuccessfully() {
        // Prepare
        clear()

        // When
        repository.save(Product("Rato", "R001"))

        // Then
        assertEquals(1, repository.count())

    }

    fun clear() {
        em.transaction.begin()
        em.createNativeQuery("truncate table products").executeUpdate()
        em.transaction.commit()
    }

}