package com.klieme.artdiary.adaptor.`in`.web.mapper

import com.klieme.artdiary.adaptor.`in`.web.response.GatheringVisitedExhResponse
import com.klieme.artdiary.adaptor.`in`.web.response.SoloVisitedExhResponse
import com.klieme.artdiary.application.dto.GatheringVisitedExhResult
import com.klieme.artdiary.application.dto.SoloVisitedExhResult

fun SoloVisitedExhResult.toResponse(): SoloVisitedExhResponse = SoloVisitedExhResponse(
    visitId = visitId,
    visitDate = visitDate,
    exhId = exhId,
    exhName = exhName,
    gallery = gallery,
    poster = poster,
    completed = completed
)

fun GatheringVisitedExhResult.toResponse(): GatheringVisitedExhResponse = GatheringVisitedExhResponse(
    visitId = visitId,
    visitDate = visitDate,
    exhId = exhId,
    exhName = exhName,
    gallery = gallery,
    poster = poster
)
