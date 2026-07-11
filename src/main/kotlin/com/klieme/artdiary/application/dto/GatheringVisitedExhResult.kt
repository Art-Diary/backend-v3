package com.klieme.artdiary.application.dto

import com.querydsl.core.annotations.QueryProjection
import java.time.LocalDate

data class GatheringVisitedExhResult
@QueryProjection
constructor(
    val visitId: Long,
    val visitDate: LocalDate,
    val exhId: Long,
    val exhName: String,
    val gallery: String,
    val poster: String
)
