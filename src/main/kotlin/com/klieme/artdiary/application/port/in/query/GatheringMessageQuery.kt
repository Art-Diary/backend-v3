package com.klieme.artdiary.application.port.`in`.query

data class GatheringMessageQuery(
    val gatheringId: Long,
    val visitId: Long,
    val questionId: Long,
    val userId: Long
)
