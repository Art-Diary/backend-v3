package com.klieme.artdiary.adaptor.`in`.web.response

import java.time.LocalDate
import java.time.LocalDateTime

data class ExhResponse(
    val exhId: Long?,
    val exhName: String,
    val gallery: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val painter: String?,
    val poster: String,
    val liked: Boolean
)

data class ExhDetailResponse(
    val exhId: Long?,
    val exhName: String,
    val gallery: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val painter: String?,
    val fee: Int,
    val intro: String?,
    val homepageLink: String?,
    val poster: String,
    val source: String,
    val liked: Boolean
)

data class ExhReviewResponse(
    val soloDiaryId: Long,
    val questionContent: String,
    val answerContent: String,
    val writeDate: LocalDateTime,
    val userId: Long,
    val nickname: String,
    val profile: String?
)