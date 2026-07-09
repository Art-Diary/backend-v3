package com.klieme.artdiary.application.dto

import com.querydsl.core.annotations.QueryProjection
import java.time.LocalDate

data class SoloVisitedExhResult
@QueryProjection
constructor(
    val visitId: Long,
    val visitDate: LocalDate,
    val exhId: Long,
    val exhName: String,
    val gallery: String,
    val poster: String,
    val completed: Boolean
)
