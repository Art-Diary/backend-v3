package com.klieme.artdiary.adaptor.`in`.web.mapper

import com.klieme.artdiary.adaptor.`in`.web.response.SoloDiaryResponse
import com.klieme.artdiary.application.dto.SoloDiaryResult

fun SoloDiaryResult.toResponse(): SoloDiaryResponse = SoloDiaryResponse(
    soloDiaryId = soloDiaryId,
    questionId = questionId,
    questionContent = questionContent,
    answerContent = answerContent,
    writeDate = writeDate,
    isPublic = isPublic
)
