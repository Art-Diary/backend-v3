package com.klieme.artdiary.application.dto

import com.querydsl.core.annotations.QueryProjection

data class GatheringMemberResult
@QueryProjection
constructor(
    val userId: Long,
    val nickname: String,
    val profile: String?
)
