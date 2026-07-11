package com.klieme.artdiary.application.port.out

import com.klieme.artdiary.application.dto.ExhReviewResult
import com.klieme.artdiary.application.dto.SoloDiaryResult

interface SoloDiaryPort {
    fun findDiaryList(
        visitId: Long,
        userId: Long
    ): List<SoloDiaryResult>

    fun save(
        visitId: Long,
        questionId: Long,
        answerContent: String,
        isPublic: Boolean
    )

    fun update(
        visitId: Long,
        userId: Long,
        soloDiaryId: Long,
        questionId: Long,
        answerContent: String,
        isPublic: Boolean
    )

    fun delete(
        visitId: Long,
        userId: Long,
        soloDiaryId: Long
    )

    fun findExhReviewList(
        exhId: Long
    ): List<ExhReviewResult>
}
