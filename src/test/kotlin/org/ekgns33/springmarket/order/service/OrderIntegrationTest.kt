package org.ekgns33.springmarket.order.service

import org.ekgns33.springmarket.order.service.port.`in`.OrderCancelCommand
import org.ekgns33.springmarket.order.service.port.`in`.OrderCreateCommand
import org.ekgns33.springmarket.order.service.port.`in`.OrderCreateUsecase
import org.ekgns33.springmarket.product.domain.ProductStatus
import org.ekgns33.springmarket.product.persistence.ProductEntity
import org.ekgns33.springmarket.product.persistence.ProductRepository
import org.ekgns33.springmarket.product.service.port.out.ProductLoadPort
import org.ekgns33.springmarket.user.domain.User
import org.ekgns33.springmarket.user.domain.UserEntity
import org.ekgns33.springmarket.user.domain.UserRole
import org.ekgns33.springmarket.user.persistence.UserRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
class OrderIntegrationTest {
    @Autowired
    private lateinit var productLoadPort: ProductLoadPort

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var productRepository: ProductRepository

    @Autowired
    lateinit var orderCreateUsecase: OrderCreateUsecase

    @Autowired
    lateinit var orderCancelUsecase: OrderCancelService

    var productId: Long = 0L

    @BeforeEach
    fun setup() {
        val u1 = User(
            email = "user@example.com", password = "password", name = "name", role = UserRole.USER,
            id = null
        )
        val u2 = User(
            email = "example@example.com", password = "password", name = "example", role = UserRole.USER,
            id = null
        )
        userRepository.save(UserEntity(u1));
        userRepository.save(UserEntity(u2))

        val productEntity = ProductEntity(
            sellerId = 2L,
            quantity = 10,
            reserved = 1,
            sold = 2,
            status = ProductStatus.ON_SALE,
            price = 3000,
            name = "상품 1"
        )
        productRepository.saveAndFlush(productEntity)
        productId = productEntity.id!!
    }

    @Test
    fun `주문 생성 후 상품 재고가 감소되어야 한다`() {
        val buyerId = 1L
        val orderQuantity = 5

        orderCreateUsecase.createOrder(
            OrderCreateCommand(
                buyerId = buyerId,
                productId = productId,
                quantity = orderQuantity,
            )
        )
        val updatedProduct = productRepository.findById(productId).get()
        assertEquals(6, updatedProduct.reserved)
        assertEquals(ProductStatus.ON_SALE, updatedProduct.status)
    }

    @Test
    fun `주문 생성 후 모든 재고가 예약되면 상품의 상태가 변경되어야 한다`() {
        val buyerId = 1L
        val orderQuantity = 7

        orderCreateUsecase.createOrder(
            OrderCreateCommand(
                buyerId = buyerId,
                productId = productId,
                quantity = orderQuantity,
            )
        )
        val updatedProduct = productRepository.findById(productId).get()
        assertEquals(8, updatedProduct.reserved)
        assertEquals(ProductStatus.RESERVED, updatedProduct.status)
    }

    @Test
    fun `주문 취소 후 상품 재고가 증가해야한다`() {
        val buyerId = 1L
        val orderQuantity = 5

        val response = orderCreateUsecase.createOrder(
            OrderCreateCommand(
                buyerId = buyerId,
                productId = productId,
                quantity = orderQuantity,
            )
        )
        var updatedProduct = productLoadPort.loadProduct(productId)
        assertEquals(2, updatedProduct.getLeftStock())

        orderCancelUsecase.cancel(
            OrderCancelCommand(
                orderId = response.orderId!!,
                userId = 2L
            )
        )
        updatedProduct = productLoadPort.loadProduct(productId)
        assertEquals(7, updatedProduct.getLeftStock())
        assertEquals(ProductStatus.ON_SALE, updatedProduct.status)
    }
}
