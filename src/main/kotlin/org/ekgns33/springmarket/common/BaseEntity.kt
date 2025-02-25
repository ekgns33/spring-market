package org.ekgns33.springmarket.common

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.io.Serializable
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(value = [AuditingEntityListener::class])
abstract class BaseEntity : Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @CreatedDate
    @Column(name = "created_at")
    var createdAt: LocalDateTime? = null

    @LastModifiedDate
    @Column(name = "updated_at")
    var updatedAt: LocalDateTime? = null

    @Column(name = "deleted_at", nullable = true)
    var deletedAt: LocalDateTime? = null

    companion object {
        const val serialVersionUID: Long = 1L
    }
}