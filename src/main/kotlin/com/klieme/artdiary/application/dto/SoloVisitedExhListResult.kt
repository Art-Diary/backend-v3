package com.klieme.artdiary.application.dto

import com.querydsl.core.annotations.QueryProjection
import java.time.LocalDate

data class SoloVisitedExhListResult
@QueryProjection
constructor(
    val exhId: Long,
    val exhName: String,
    val gallery: String,
    val poster: String,
    val visitDate: LocalDate
)
