package com.klieme.artdiary.application.dto

data class GatheringListDto(
    val id: Long,
    val name: String,
    val exhibitionCount: Long,
    val memberList: List<MemberListDto>
)

data class MemberListDto(
    val userId: Long,
    val nickname: String,
    val profile: String?
)
