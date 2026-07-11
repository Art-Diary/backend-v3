package com.klieme.artdiary.adaptor.`in`.web.request

import java.time.LocalDate

data class SoloVisitRequest(
    val exhId: Long,
    val visitDate: LocalDate,
)

data class SoloDiaryRequest(
    val questionId: Long,
    val answerContent: String,
    val isPublic: Boolean
)
