package com.klieme.artdiary.application.service.query

import com.klieme.artdiary.application.dto.SoloVisitDiaryListResult
import com.klieme.artdiary.application.dto.SoloVisitTicketListResult
import com.klieme.artdiary.application.port.`in`.query.SoloVisitDiaryListQuery
import com.klieme.artdiary.application.port.`in`.query.SoloVisitQueryUseCase
import com.klieme.artdiary.application.port.out.SoloVisitPort
import org.springframework.stereotype.Service

@Service
class SoloVisitQueryService(
    private val soloVisitPort: SoloVisitPort
) : SoloVisitQueryUseCase {
    override fun getTicketList(userId: Long): List<SoloVisitTicketListResult> {
        return soloVisitPort.findTicketList(userId)
    }

    override fun getDiaryList(query: SoloVisitDiaryListQuery): List<SoloVisitDiaryListResult> {
        return soloVisitPort.findDiaryList(query.exhId, query.userId)
    }
}