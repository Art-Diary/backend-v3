package com.klieme.artdiary.application.port.`in`.query

import com.klieme.artdiary.application.dto.ExhDetailResult
import com.klieme.artdiary.application.dto.ExhListResult
import com.klieme.artdiary.application.dto.ExhReviewResult
import org.springframework.data.domain.Slice

interface ExhQueryUseCase {
    fun getExhList(query: ExhListQuery): Slice<ExhListResult>

    fun getExhDetail(query: ExhDetailQuery): ExhDetailResult

    fun getExhReviewList(query: ExhDetailQuery): List<ExhReviewResult>
}
