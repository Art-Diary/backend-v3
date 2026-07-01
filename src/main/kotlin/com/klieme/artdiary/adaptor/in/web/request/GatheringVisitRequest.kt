package com.klieme.artdiary.adaptor.`in`.web.request

import java.time.LocalDate

data class GatheringVisitRequest(
    val exhId: Long,
    val visitDate: LocalDate
)
