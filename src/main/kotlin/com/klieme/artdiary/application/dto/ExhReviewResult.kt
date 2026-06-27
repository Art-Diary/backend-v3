package com.klieme.artdiary.application.dto

import com.querydsl.core.annotations.QueryProjection
import java.time.LocalDateTime

data class ExhReviewResult
@QueryProjection
constructor(
    val soloDiaryId: Long,
    val questionContent: String,
    val answerContent: String,
    val writeDate: LocalDateTime,
    val userId: Long,
    val nickname: String,
    val profile: String?
)
