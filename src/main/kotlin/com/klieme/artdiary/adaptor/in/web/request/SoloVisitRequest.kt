package com.klieme.artdiary.adaptor.`in`.web.request

import java.time.LocalDateTime

data class SoloVisitRequest(
    val questionId: Long,
    val answerContent: String,
    val writeDate: LocalDateTime,
    val isPublic: Boolean
)