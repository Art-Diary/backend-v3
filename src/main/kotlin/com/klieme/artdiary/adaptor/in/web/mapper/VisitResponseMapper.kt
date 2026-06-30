package com.klieme.artdiary.adaptor.`in`.web.mapper

import com.klieme.artdiary.adaptor.`in`.web.response.VisitedExhResponse
import com.klieme.artdiary.application.dto.VisitedExhResult

fun VisitedExhResult.toResponse(): VisitedExhResponse = VisitedExhResponse(
    visitId = visitId,
    visitDate = visitDate,
    exhId = exhId,
    exhName = exhName,
    gallery = gallery,
    poster = poster
)
