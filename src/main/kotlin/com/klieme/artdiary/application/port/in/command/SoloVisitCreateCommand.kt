package com.klieme.artdiary.application.port.`in`.command

import java.time.LocalDate

data class SoloVisitCreateCommand(
    val exhId: Long,
    val userId: Long,
    val visitDate: LocalDate,
)

data class SoloDiaryCreateCommand(
    val visitId: Long,
    val userId: Long,
    val diaryList: List<DiaryListCommand>
)

data class DiaryListCommand(
    val questionId: Long,
    val answerContent: String,
    val isPublic: Boolean
)

data class SoloDiaryUpdateCommand(
    val visitId: Long,
    val userId: Long,
    val soloDiaryId: Long,
    val questionId: Long,
    val answerContent: String,
    val isPublic: Boolean
)

data class SoloDiaryDeleteCommand(
    val visitId: Long,
    val userId: Long,
    val soloDiaryId: Long,
)
