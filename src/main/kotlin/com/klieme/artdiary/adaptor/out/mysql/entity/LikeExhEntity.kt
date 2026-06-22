package com.klieme.artdiary.adaptor.out.mysql.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(
    name = "like_exh",
    schema = "public",
    uniqueConstraints = [
        UniqueConstraint(columnNames = ["exh_id", "user_id"])
    ]
)
class LikeExhEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    var id: Long? = null,

    @Column(name = "exh_id", nullable = false)
    var exhId: Long,

    @Column(name = "user_id", nullable = false)
    var userId: Long,

    @Column(name = "created_at", nullable = false)
    var createdAt: LocalDateTime
) {

}