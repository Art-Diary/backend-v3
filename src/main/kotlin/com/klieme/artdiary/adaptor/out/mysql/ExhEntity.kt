package com.klieme.artdiary.adaptor.out.mysql

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "exhibition", schema = "public")
class ExhEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exh_id")
    var exhId: Long? = null,

    @Column(name = "exh_name", nullable = false)
    var exhName: String,

    @Column(name = "gallery", nullable = false)
    var gallery: String,

    @Column(name = "start_date", nullable = false)
    var startDate: LocalDate,

    @Column(name = "end_date", nullable = false)
    var endDate: LocalDate,

    @Column(name = "painter")
    var painter: String? = null,

    @Column(name = "fee", nullable = false)
    var fee: Int,

    @Column(name = "intro", columnDefinition = "LONGTEXT")
    var intro: String? = null,

    @Column(name = "homepage_link", length = 2083)
    var homepageLink: String? = null,

    @Column(name = "poster", nullable = false)
    var poster: String,

    @Column(name = "art_field")
    var artField: String? = null,

    @Column(name = "source", nullable = false)
    var source: String
)
