package org.ekgns33.springmarket.order.service

import org.ekgns33.springmarket.common.Money
import org.ekgns33.springmarket.order.domain.Order
import org.ekgns33.springmarket.order.domain.OrderLine
import org.ekgns33.springmarket.order.domain.OrderStatus
import org.ekgns33.springmarket.order.persistence.OrderEntity
import org.ekgns33.springmarket.order.persistence.OrderRepository
import org.ekgns33.springmarket.order.service.dtos.Seller
import org.ekgns33.springmarket.order.service.port.`in`.OrderConfirmCommand
import org.ekgns33.springmarket.order.service.port.out.OrderUserLoadPort
import org.junit.jupiter.api.BeforeEach
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.bean.override.mockito.MockitoBean
import kotlin.test.Test
import kotlin.test.assertEquals

@SpringBootTest
class OrderConfirmServiceTest {

    val mockOrder = Order.withoutId(
        sellerId = 1L,
        buyerId = 2L,
        orderLine = OrderLine(
            productId = 11L,
            price = Money(3000),
            quantity = 2,
            totalAmount = Money(6000)
        ),
        status = OrderStatus.REQUESTED
    )

    @Autowired
    private lateinit var orderRepository: OrderRepository

    @MockitoBean
    private lateinit var orderUserLoadPort: OrderUserLoadPort

    @Autowired
    private lateinit var service: OrderConfirmService

    @BeforeEach
    fun setUp() {
        orderRepository.save(OrderEntity(mockOrder))
    }

    @Test
    fun `주문 확정 테스트`() {
        whenever(orderUserLoadPort.loadSeller(any()))
            .thenReturn(Seller(1L, "Name"))

        val response = service.confirmOrder(OrderConfirmCommand(1L, 1L))
        val savedOrder = orderRepository.findById(1L)
        assertEquals(OrderStatus.CONFIRMED, savedOrder.get().status)
        assertEquals(1L, response.orderId)
    }
}