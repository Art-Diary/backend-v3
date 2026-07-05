package com.klieme.artdiary.adaptor.`in`.web.response

import java.time.LocalDate

data class CalendarResponse(
    val visitId: Long,
    val visitDate: LocalDate,
    val exhId: Long,
    val exhName: String,
    val poster: String
)
