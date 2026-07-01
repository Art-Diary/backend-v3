package com.klieme.artdiary.application.port.`in`.query

data class GatheringQuestionQuery(
    val gatheringId: Long,
    val visitId: Long,
    val userId: Long
)
