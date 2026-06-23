package com.klieme.artdiary.application.port.`in`.query

import com.klieme.artdiary.application.dto.ExhDetailResult
import com.klieme.artdiary.application.dto.ExhListResult
import org.springframework.data.domain.Slice

interface ExhQueryUseCase {
    fun getExhList(query: ExhListQuery): Slice<ExhListResult>

    fun getExhDetail(query: ExhDetailQuery): ExhDetailResult
}
