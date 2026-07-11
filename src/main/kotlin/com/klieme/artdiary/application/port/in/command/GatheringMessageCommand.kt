package com.klieme.artdiary.application.port.`in`.command

data class GatheringMessageCreateCommand(
    val gatheringId: Long,
    val visitId: Long,
    val questionId: Long,
    val userId: Long,
    val content: String
)

data class GatheringMessageUpdateCommand(
    val messageId: Long,
    val userId: Long,
    val content: String
)

data class GatheringMessageDeleteCommand(
    val messageId: Long,
    val userId: Long
)
