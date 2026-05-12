package com.klieme.artdiary.application.service.query

import com.klieme.artdiary.application.port.`in`.query.ExhQueryUseCase
import com.klieme.artdiary.application.port.out.ExhPort
import com.klieme.artdiary.domain.Exh
import org.springframework.stereotype.Service

@Service
class ExhQueryService(
    private val exhPort: ExhPort
): ExhQueryUseCase {
    override fun get(exhId: Long): Exh {
        return exhPort.findByExhId(exhId)
    }
}
