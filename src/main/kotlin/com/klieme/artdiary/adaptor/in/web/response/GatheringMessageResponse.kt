package com.klieme.artdiary.adaptor.`in`.web.response

import java.time.LocalDateTime

data class GatheringMessageResponse(
    val id: Long,
    val content: String,
    val createdAt: LocalDateTime,
    val userId: Long,
    val nickname: String,
    val profile: String?
)
