package com.klieme.artdiary.application.port.out

import com.klieme.artdiary.application.dto.ExhDetailResult
import com.klieme.artdiary.application.dto.ExhListResult
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import java.time.LocalDate

interface ExhPort {
    fun existsByExhId(exhId: Long): Boolean

    fun increaseLikeCount(exhId: Long)

    fun decreaseLikeCount(exhId: Long)

    fun findList(
        keyword: String? = null,
        date: LocalDate? = null,
        userId: Long,
        pageable: Pageable,
    ): Slice<ExhListResult>

    fun findDetail(
        exhId: Long,
        userId: Long,
    ): ExhDetailResult
}
