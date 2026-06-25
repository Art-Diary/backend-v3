package com.klieme.artdiary.adaptor.out.mysql.entity

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(
    name = "visit",
    schema = "public",
    uniqueConstraints = [
        UniqueConstraint(columnNames = ["ticket_id", "visit_date"])
    ]
)
class VisitEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    var id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_id", nullable = false)
    val ticket: TicketEntity,

    @Column(name = "visit_date", nullable = false)
    var visitDate: LocalDate,
)