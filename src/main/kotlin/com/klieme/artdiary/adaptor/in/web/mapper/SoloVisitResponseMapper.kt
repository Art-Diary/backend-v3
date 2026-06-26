package com.klieme.artdiary.adaptor.`in`.web.mapper

import com.klieme.artdiary.adaptor.`in`.web.response.SoloDiaryResponse
import com.klieme.artdiary.adaptor.`in`.web.response.SoloDiaryListResponse
import com.klieme.artdiary.adaptor.`in`.web.response.SoloVisitedExhListResponse
import com.klieme.artdiary.application.dto.SoloDiaryListResult
import com.klieme.artdiary.application.dto.SoloVisitedExhListResult

fun SoloVisitedExhListResult.toListResponse(): SoloVisitedExhListResponse = SoloVisitedExhListResponse(
    exhId = exhId,
    exhName = exhName,
    gallery = gallery,
    poster = poster,
    visitDate = visitDate
)

fun List<SoloDiaryListResult>.toListResponse(): List<SoloDiaryListResponse> =
    groupBy { it.visitId }
        .map { (_, results) ->

            val first = results.first()

            SoloDiaryListResponse(
                visitId = first.visitId,
                visitDate = first.visitDate,
                diaries = results.map {
                    SoloDiaryResponse(
                        soloDiaryId = it.soloDiaryId,
                        questionId = it.questionId,
                        questionContent = it.questionContent,
                        answerContent = it.answerContent,
                        writeDate = it.writeDate,
                        isPublic = it.isPublic,
                    )
                }
            )
        }