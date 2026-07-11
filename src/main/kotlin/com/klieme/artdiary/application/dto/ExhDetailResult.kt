package com.klieme.artdiary.application.dto

import com.querydsl.core.annotations.QueryProjection
import java.time.LocalDate

data class ExhDetailResult
@QueryProjection
constructor(
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
    val source: String,
    val liked: Boolean,
    val visited: Boolean
)
