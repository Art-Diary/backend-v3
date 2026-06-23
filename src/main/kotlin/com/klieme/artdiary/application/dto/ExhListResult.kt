package com.klieme.artdiary.application.dto

import com.querydsl.core.annotations.QueryProjection
import java.time.LocalDate

data class ExhListResult
@QueryProjection
constructor(
    val exhId: Long?,
    val exhName: String,
    val gallery: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val painter: String?,
    val poster: String,
    val liked: Boolean
)