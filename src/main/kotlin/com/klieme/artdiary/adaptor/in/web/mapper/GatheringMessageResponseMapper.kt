package com.klieme.artdiary.adaptor.`in`.web.mapper

import com.klieme.artdiary.adaptor.`in`.web.response.GatheringMessageResponse
import com.klieme.artdiary.application.dto.GatheringMessageResult

fun GatheringMessageResult.toResponse(): GatheringMessageResponse = GatheringMessageResponse(
    id = id,
    content = content,
    createdAt = createdAt,
    userId = userId,
    nickname = nickname,
    profile = profile
)
