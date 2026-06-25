package com.klieme.artdiary.adaptor.out.mysql.entity

import jakarta.persistence.*

@Entity
@Table(
    name = "ticket",
    schema = "public",
    uniqueConstraints = [
        UniqueConstraint(columnNames = ["exh_id", "user_id"])
    ]
)
class TicketEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    var id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exh_id", nullable = false)
    val exh: ExhEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: UserEntity? = null,
)