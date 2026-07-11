package com.klieme.artdiary.adaptor.`in`.web.response

import java.time.LocalDateTime

data class SoloDiaryResponse(
    val soloDiaryId: Long,
    val questionId: Long,
    val questionContent: String,
    val answerContent: String,
    val writeDate: LocalDateTime,
    val isPublic: Boolean,
)
