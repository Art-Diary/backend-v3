package com.klieme.artdiary.adaptor.`in`.web.response

import java.time.LocalDate

data class GatheringVisitedExhResponse(
    val visitId: Long,
    val visitDate: LocalDate,
    val exhId: Long,
    val exhName: String,
    val gallery: String,
    val poster: String
)
