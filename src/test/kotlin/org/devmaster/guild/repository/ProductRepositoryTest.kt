package org.devmaster.guild.repository

import org.devmaster.guild.model.Product
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
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
    fun shouldFindProductBySky() {
        // Given
        initDbWithProducts(arrayOf(Product("Mouse", "M001"), Product("Monitor", "M002")))

        // When
        val product = repository.findOneBySku("M002")

        // Then
        assertNotNull(product)
        assertEquals("Monitor", product?.name)
    }

    @Test(expected = Exception::class)
    fun whenAddProductWithExistingSku_shouldFail() {
        // Prepare
        initDbWithProducts(arrayOf(Product("Mouse", "M001")))

        // When
        repository.save(Product("Monitor", "M001"))
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

    fun initDbWithProducts(products: Array<Product>) {
        clear()

        em.transaction.begin()
        products.forEach {
            em.persist(it)
        }
        em.transaction.commit()
    }

    fun clear() {
        em.transaction.begin()
        em.createNativeQuery("truncate table products").executeUpdate()
        em.transaction.commit()
    }

}