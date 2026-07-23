package com.klieme.artdiary.adaptor.`in`.web.response

data class GatheringResponse(
    val id: Long,
    val name: String,
    val exhibitionCount: Long,
    val memberList: List<GatheringMemberResponse>
)

data class GatheringDetailResponse(
    val id: Long,
    val name: String,
    val code: String,
    val memberList: List<GatheringMemberResponse>,
    val visitedExhList: List<GatheringVisitedExhResponse>
)

data class GatheringMemberResponse(
    val id: Long,
    val nickname: String,
    val profile: String?,
)
