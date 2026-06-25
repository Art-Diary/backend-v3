package com.klieme.artdiary.adaptor.`in`.web.mapper

import com.klieme.artdiary.adaptor.`in`.web.response.SoloDiaryResponse
import com.klieme.artdiary.adaptor.`in`.web.response.SoloVisitDiaryListResponse
import com.klieme.artdiary.adaptor.`in`.web.response.SoloVisitTicketListResponse
import com.klieme.artdiary.application.dto.SoloVisitDiaryListResult
import com.klieme.artdiary.application.dto.SoloVisitTicketListResult

fun SoloVisitTicketListResult.toListResponse(): SoloVisitTicketListResponse = SoloVisitTicketListResponse(
    exhId = exhId,
    exhName = exhName,
    gallery = gallery,
    poster = poster,
    visitDate = visitDate
)

fun List<SoloVisitDiaryListResult>.toListResponse(): List<SoloVisitDiaryListResponse> =
    groupBy { it.visitId }
        .map { (_, results) ->

            val first = results.first()

            SoloVisitDiaryListResponse(
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