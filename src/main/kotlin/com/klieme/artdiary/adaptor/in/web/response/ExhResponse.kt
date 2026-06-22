package com.klieme.artdiary.adaptor.`in`.web.response

import java.time.LocalDate

data class ExhListResponse (
    val exhId: Long?,
    val exhName: String,
    val gallery: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val painter: String?,
    val poster: String,
)

data class ExhResponse (
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
    val source: String
)
