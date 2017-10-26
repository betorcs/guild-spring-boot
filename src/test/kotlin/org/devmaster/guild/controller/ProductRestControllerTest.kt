package org.devmaster.guild.controller

import org.devmaster.guild.model.Product
import org.devmaster.guild.repository.ProductRepository
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.*
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ProductRestControllerTest {

    @LocalServerPort
    var port: Int = 0

    @MockBean
    lateinit var repository: ProductRepository

    @Autowired
    lateinit var restTemplate: TestRestTemplate



    @Test
    fun save() {
        // Given
        val productSend = Product("Teclado", "K004")
        val productReturn = Product("Teclado", "K004", 100)

        Mockito.`when`(repository.save(productSend)).thenReturn(productReturn)


        val headers = HttpHeaders().apply { contentType = MediaType.APPLICATION_JSON }
        val entity = HttpEntity<Product>(productSend, headers)


        // When
        val response = restTemplate.postForEntity("http://localhost:$port/product", entity, Product::class.java)


        // Then
        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(response.body, productReturn)

    }

    @Test
    fun delete() {

        val productId = 213L

        val productIdCaptor = ArgumentCaptor.forClass(Long::class.java)


        restTemplate.delete("http://localhost:$port/product/$productId")

        Mockito.verify(repository).delete(productIdCaptor.capture())

        assertEquals(productId, productIdCaptor.value)
    }

    @Test
    fun findBySku() {
        val product = Product("Teclado", "K234", 1)
        val sku = product.sku

        Mockito.`when`(repository.findOneBySku(sku)).thenReturn(product)

        val response = restTemplate.getForEntity("http://localhost:$port/product/$sku", Product::class.java)

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(product, response.body)
    }

    @Test
    fun findAll() {
        val products = listOf(Product("Teclado", "K234", 1), Product("Mouse", "M133", 3))
        Mockito.`when`(repository.findAll())
                .thenReturn(products)

        val response = restTemplate.exchange(
                "http://localhost:$port/products",
                HttpMethod.GET,
                null,
                object : ParameterizedTypeReference<List<Product>>(){})

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(products, response.body)
    }

}