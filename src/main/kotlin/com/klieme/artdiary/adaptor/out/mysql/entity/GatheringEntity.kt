package com.klieme.artdiary.adaptor.out.mysql.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.SQLDelete
import java.time.LocalDateTime

@Entity
@Table(
    name = "gathering",
    schema = "public",
    uniqueConstraints = [
        UniqueConstraint(columnNames = ["code"])
    ]
)
@SQLDelete(
    sql = """
    UPDATE gathering
    SET deleted_at = CURRENT_TIMESTAMP
    WHERE id = ?
"""
)
class GatheringEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null,

    @Column(name = "name", nullable = false, length = 20)
    var name: String,

    @Column(name = "code", nullable = false, length = 6, unique = true)
    var code: String,

    @CreationTimestamp
    @Column(
        name = "created_at",
        nullable = false,
        updatable = false
    )
    var createdAt: LocalDateTime? = null,

    @Column(name = "deleted_at")
    var deletedAt: LocalDateTime? = null,
)
