package com.klieme.artdiary.adaptor.`in`.web.mapper

import com.klieme.artdiary.adaptor.`in`.web.response.CalendarResponse
import com.klieme.artdiary.application.dto.CalendarVisitResult

fun CalendarVisitResult.toResponse(): CalendarResponse = CalendarResponse(
    visitId = visitId,
    visitDate = visitDate,
    exhId = exhId,
    exhName = exhName,
    poster = poster
)
