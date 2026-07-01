package com.klieme.artdiary.adaptor.out.mysql.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.SQLDelete
import java.time.LocalDateTime

@Entity
@Table(
    name = "gathering_member",
    schema = "public",
    uniqueConstraints = [
        UniqueConstraint(
            columnNames = [
                "user_id",
                "gathering_id"
            ]
        )
    ]
)
@SQLDelete(
    sql = """
    UPDATE gathering_member
    SET deleted_at = CURRENT_TIMESTAMP
    WHERE id = ?
"""
)
class GatheringMemberEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null,

    @CreationTimestamp
    @Column(
        name = "created_at",
        nullable = false,
        updatable = false
    )
    var createdAt: LocalDateTime? = null,

    @Column(name = "deleted_at")
    var deletedAt: LocalDateTime? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    var user: UserEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gathering_id", nullable = false)
    var gathering: GatheringEntity,
)
