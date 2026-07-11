package com.klieme.artdiary.adaptor.out.mysql.entity

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(
    name = "visit",
    schema = "public",
    uniqueConstraints = [
        UniqueConstraint(
            columnNames = [
                "exh_id",
                "user_id",
                "visit_date"
            ]
        ),
        UniqueConstraint(
            columnNames = [
                "exh_id",
                "gathering_id",
                "visit_date"
            ]
        )
    ]
)
class VisitEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    var id: Long? = null,

    @Column(name = "visit_date", nullable = false)
    var visitDate: LocalDate,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exh_id", nullable = false)
    var exh: ExhEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: UserEntity? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gathering_id")
    var gathering: GatheringEntity? = null,
)
