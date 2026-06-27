package com.klieme.artdiary.application.port.`in`.command

import java.time.LocalDate
import java.time.LocalDateTime

data class SoloVisitCreateCommand(
    val exhId: Long,
    val userId: Long,
    val visitDate: LocalDate,
)

data class SoloDiaryCreateCommand(
    val visitId: Long,
    val userId: Long,
    val questionId: Long,
    val answerContent: String,
    val writeDate: LocalDateTime,
    val isPublic: Boolean
)

data class SoloDiaryUpdateCommand(
    val visitId: Long,
    val userId: Long,
    val soloDiaryId: Long,
    val questionId: Long,
    val answerContent: String,
    val writeDate: LocalDateTime,
    val isPublic: Boolean
)

data class SoloDiaryDeleteCommand(
    val visitId: Long,
    val userId: Long,
    val soloDiaryId: Long,
)