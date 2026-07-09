package com.klieme.artdiary.application.service.query

import com.klieme.artdiary.application.dto.SoloDiaryResult
import com.klieme.artdiary.application.dto.SoloVisitedExhResult
import com.klieme.artdiary.application.port.`in`.query.SoloDiaryListQuery
import com.klieme.artdiary.application.port.`in`.query.SoloVisitQueryUseCase
import com.klieme.artdiary.application.port.out.SoloDiaryPort
import com.klieme.artdiary.application.port.out.VisitPort
import org.springframework.stereotype.Service

@Service
class SoloVisitQueryService(
    private val visitPort: VisitPort,
    private val soloDiaryPort: SoloDiaryPort
) : SoloVisitQueryUseCase {
    override fun getVisitedExhList(userId: Long): List<SoloVisitedExhResult> {
        return visitPort.findSoloVisitedExhList(userId)
    }

    override fun getDiaryList(query: SoloDiaryListQuery): List<SoloDiaryResult> {
        return soloDiaryPort.findDiaryList(query.visitId, query.userId)
    }
}
