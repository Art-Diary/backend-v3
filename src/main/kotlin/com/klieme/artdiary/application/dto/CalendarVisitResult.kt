package com.klieme.artdiary.application.dto

import com.querydsl.core.annotations.QueryProjection
import java.time.LocalDate

data class CalendarVisitResult
@QueryProjection
constructor(
    val visitId: Long,
    val visitDate: LocalDate,
    val exhId: Long,
    val exhName: String,
    val poster: String
)
