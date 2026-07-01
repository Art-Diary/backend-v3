package com.klieme.artdiary.application.port.`in`.command

import java.time.LocalDate

data class GatheringCreateCommand(
    val userId: Long,
    val name: String
)

data class GatheringJoinCommand(
    val userId: Long,
    val code: String
)

data class GatheringVisitExhCommand(
    val userId: Long,
    val gatheringId: Long,
    val exhId: Long,
    val visitDate: LocalDate
)
