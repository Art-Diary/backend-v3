package com.klieme.artdiary.application.service.query

import com.klieme.artdiary.application.dto.SoloDiaryListResult
import com.klieme.artdiary.application.dto.SoloVisitedExhListResult
import com.klieme.artdiary.application.port.`in`.query.SoloDiaryListQuery
import com.klieme.artdiary.application.port.`in`.query.SoloVisitQueryUseCase
import com.klieme.artdiary.application.port.out.SoloVisitPort
import org.springframework.stereotype.Service

@Service
class SoloVisitQueryService(
    private val soloVisitPort: SoloVisitPort
) : SoloVisitQueryUseCase {
    override fun getVisitedExhList(userId: Long): List<SoloVisitedExhListResult> {
        return soloVisitPort.findVisitedExhList(userId)
    }

    override fun getDiaryList(query: SoloDiaryListQuery): List<SoloDiaryListResult> {
        return soloVisitPort.findDiaryList(query.exhId, query.userId)
    }
}