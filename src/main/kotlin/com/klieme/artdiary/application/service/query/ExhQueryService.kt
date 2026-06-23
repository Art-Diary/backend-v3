package com.klieme.artdiary.application.service.query

import com.klieme.artdiary.application.dto.ExhDetailResult
import com.klieme.artdiary.application.dto.ExhListResult
import com.klieme.artdiary.application.port.`in`.query.ExhDetailQuery
import com.klieme.artdiary.application.port.`in`.query.ExhListQuery
import com.klieme.artdiary.application.port.`in`.query.ExhQueryUseCase
import com.klieme.artdiary.application.port.out.ExhPort
import org.springframework.data.domain.Slice
import org.springframework.stereotype.Service

@Service
class ExhQueryService(
    private val exhPort: ExhPort
) : ExhQueryUseCase {
    override fun getExhList(query: ExhListQuery): Slice<ExhListResult> {
        return if (!query.keyword.isNullOrBlank()) {
            exhPort.findList(
                query.keyword,
                null,
                query.userId,
                pageable = query.pageable,
            )
        } else if (query.date != null) {
            exhPort.findList(
                null,
                query.date,
                query.userId,
                pageable = query.pageable,
            )
        } else {
            exhPort.findList(
                null,
                null,
                query.userId,
                pageable = query.pageable,
            )
        }
    }

    override fun getExhDetail(query: ExhDetailQuery): ExhDetailResult {
        return exhPort.findDetail(query.exhId, query.userId)
    }
}
