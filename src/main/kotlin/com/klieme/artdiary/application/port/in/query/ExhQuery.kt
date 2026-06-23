package com.klieme.artdiary.application.port.`in`.query

import org.springframework.data.domain.Pageable
import java.time.LocalDate

data class ExhListQuery(
    val keyword: String?,
    val date: LocalDate?,
    val userId: Long,
    val pageable: Pageable,
)

data class ExhDetailQuery(
    val exhId: Long,
    val userId: Long
)
