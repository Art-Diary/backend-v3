package com.klieme.artdiary.adaptor.`in`.web.mapper

import com.klieme.artdiary.adaptor.`in`.web.response.SoloDiaryResponse
import com.klieme.artdiary.adaptor.`in`.web.response.SoloVisitedExhResponse
import com.klieme.artdiary.application.dto.SoloDiaryResult
import com.klieme.artdiary.application.dto.SoloVisitedExhResult

fun SoloVisitedExhResult.toResponse(): SoloVisitedExhResponse = SoloVisitedExhResponse(
    visitId = visitId,
    visitDate = visitDate,
    exhId = exhId,
    exhName = exhName,
    gallery = gallery,
    poster = poster
)

fun SoloDiaryResult.toResponse(): SoloDiaryResponse = SoloDiaryResponse(
    soloDiaryId = soloDiaryId,
    questionId = questionId,
    questionContent = questionContent,
    answerContent = answerContent,
    writeDate = writeDate,
    isPublic = isPublic
)