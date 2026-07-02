package com.klieme.artdiary.application.dto

import com.querydsl.core.annotations.QueryProjection
import java.time.LocalDateTime

data class GatheringMessageResult
@QueryProjection
constructor(
    val id: Long,
    val content: String,
    val createdAt: LocalDateTime,
    val userId: Long,
    val nickname: String,
    val profile: String?
)
