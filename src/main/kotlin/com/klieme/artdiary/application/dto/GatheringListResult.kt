package com.klieme.artdiary.application.dto

import com.querydsl.core.annotations.QueryProjection

data class GatheringListResult
@QueryProjection
constructor(
    val id: Long,
    val name: String,
    val exhibitionCount: Long,
    val userId: Long,
    val nickname: String,
    val profile: String?
)
