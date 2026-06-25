package com.klieme.artdiary.adaptor.`in`.web.response

import java.time.LocalDate
import java.time.LocalDateTime

data class SoloVisitTicketListResponse(
    val exhId: Long,
    val exhName: String,
    val gallery: String,
    val poster: String,
    val visitDate: LocalDate
)

data class SoloVisitDiaryListResponse(
    val visitId: Long,
    val visitDate: LocalDate,
    val diaries: List<SoloDiaryResponse>,
)

data class SoloDiaryResponse(
    val soloDiaryId: Long,
    val questionId: Long,
    val questionContent: String,
    val answerContent: String,
    val writeDate: LocalDateTime,
    val isPublic: Boolean,
)