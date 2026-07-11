package com.klieme.artdiary.application.port.`in`.command

data class GatheringQuestionCreateCommand(
    val gatheringId: Long,
    val visitId: Long,
    val userId: Long,
    val content: String
)

data class GatheringQuestionUpdateCommand(
    val gatheringId: Long,
    val visitId: Long,
    val userId: Long,
    val questionId: Long,
    val content: String
)
