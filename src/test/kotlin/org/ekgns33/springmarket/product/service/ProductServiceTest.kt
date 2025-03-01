package org.ekgns33.springmarket.product.service


import org.ekgns33.springmarket.product.domain.ProductStatus
import org.ekgns33.springmarket.product.domain.Seller
import org.ekgns33.springmarket.product.persistence.ProductEntity
import org.ekgns33.springmarket.product.persistence.ProductRepository
import org.ekgns33.springmarket.product.service.port.`in`.ProductUpdateUsecase
import org.ekgns33.springmarket.product.service.port.`in`.model.ProductStockUseCommand
import org.ekgns33.springmarket.product.service.port.out.ProductUserLoadPort
import org.ekgns33.springmarket.user.domain.User
import org.ekgns33.springmarket.user.domain.UserEntity
import org.ekgns33.springmarket.user.persistence.UserRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.bean.override.mockito.MockitoBean
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

@SpringBootTest
@AutoConfigureMockMvc
class ProductServiceTest {

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    lateinit var productUpdateService: ProductUpdateUsecase

    @Autowired
    private lateinit var productRepository: ProductRepository

    @MockitoBean
    private lateinit var productUserLoadPort: ProductUserLoadPort

    var pid = 0L

    @BeforeEach
    fun setup() {
        // 테스트용 상품 생성: 재고 10, 상태 ON_SALE
        val productEntity = ProductEntity(
            sellerId = 1,
            status = ProductStatus.ON_SALE,
            quantity = 30,
            reserved = 0,
            sold = 0,
            price = 10000,
            name = "name"
        )
        val en = productRepository.save(productEntity)
        pid = en.id!!

        val user = User.withoutId("hi@email.com", "hi", "pwpw")
        userRepository.save(UserEntity(user))
    }

    @Test
    fun `동시 업데이트 시 재고가 올바르게 감소해야 한다`() {
        whenever(productUserLoadPort.loadSeller(any())).thenReturn(Seller(1L, "hi"))
        val threadCount = 6
        val latch = CountDownLatch(threadCount)
        val executor = Executors.newFixedThreadPool(threadCount)

        repeat(threadCount) {
            executor.submit {
                latch.countDown()
                latch.await()
                productUpdateService.useStockForReservation(ProductStockUseCommand(1L, 5))
            }
        }
        executor.shutdown()
        executor.awaitTermination(10, TimeUnit.SECONDS)

        val updatedProductEntity = productRepository.findById(pid).get()
        assertEquals(30, updatedProductEntity.reserved)
    }
}