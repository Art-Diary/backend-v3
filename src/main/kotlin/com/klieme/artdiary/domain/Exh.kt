package com.klieme.artdiary.domain

import java.time.LocalDate

data class Exh(
    val exhId: Long?,
    val exhName: String,
    val gallery: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val painter: String?,
    val fee: Int,
    val intro: String?,
    val homepageLink: String?,
    val poster: String,
    val artField: String?,
    val source: String
)
