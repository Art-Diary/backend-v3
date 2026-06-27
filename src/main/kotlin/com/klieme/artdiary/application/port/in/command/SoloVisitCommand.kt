package com.klieme.artdiary.application.port.`in`.command

import java.time.LocalDateTime

data class SoloVisitCreateCommand(
    val visitId: Long,
    val userId: Long,
    val questionId: Long,
    val answerContent: String,
    val writeDate: LocalDateTime,
    val isPublic: Boolean
)

data class SoloVisitUpdateCommand(
    val visitId: Long,
    val userId: Long,
    val soloDiaryId: Long,
    val questionId: Long,
    val answerContent: String,
    val writeDate: LocalDateTime,
    val isPublic: Boolean
)

data class SoloVisitDeleteCommand(
    val visitId: Long,
    val userId: Long,
    val soloDiaryId: Long,
)