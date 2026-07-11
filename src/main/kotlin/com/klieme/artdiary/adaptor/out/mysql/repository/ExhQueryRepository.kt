package com.klieme.artdiary.adaptor.out.mysql.repository

import com.klieme.artdiary.application.dto.ExhDetailResult
import com.klieme.artdiary.application.dto.ExhListResult
import org.springframework.data.domain.Slice
import org.springframework.data.domain.Pageable
import java.time.LocalDate

interface ExhQueryRepository {
    fun findList(
        keyword: String? = null,
        date: LocalDate? = null,
        userId: Long,
        pageable: Pageable,
    ): Slice<ExhListResult>

    fun findDetail(
        exhId: Long,
        userId: Long,
    ): ExhDetailResult?
}