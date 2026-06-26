package com.klieme.artdiary.application.dto

import com.querydsl.core.annotations.QueryProjection
import java.time.LocalDate
import java.time.LocalDateTime

data class SoloDiaryListResult
@QueryProjection
constructor(
    val visitId: Long,
    val visitDate: LocalDate,
    val soloDiaryId: Long,
    val questionId: Long,
    val questionContent: String,
    val answerContent: String,
    val writeDate: LocalDateTime,
    val isPublic: Boolean,
)
