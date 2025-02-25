package org.ekgns33.springmarket.common

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(value = [AuditingEntityListener::class])
abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @CreatedDate
    @Column(name = "created_at")
    open var createdAt: LocalDateTime? = null

    @LastModifiedDate
    @Column(name = "updated_at")
    open var updatedAt: LocalDateTime? = null

    open var deletedAt: LocalDateTime? = null
}