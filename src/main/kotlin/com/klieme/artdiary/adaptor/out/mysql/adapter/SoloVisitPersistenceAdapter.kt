package com.klieme.artdiary.adaptor.out.mysql.adapter

import com.klieme.artdiary.adaptor.out.mysql.repository.SoloVisitQueryRepository
import com.klieme.artdiary.application.dto.SoloVisitDiaryListResult
import com.klieme.artdiary.application.dto.SoloVisitTicketListResult
import com.klieme.artdiary.application.port.out.SoloVisitPort
import org.springframework.stereotype.Component

@Component
class SoloVisitPersistenceAdapter(
    private val queryRepository: SoloVisitQueryRepository
) : SoloVisitPort {
    override fun findTicketList(userId: Long): List<SoloVisitTicketListResult> {
        return queryRepository.findTicketList(userId)
    }

    override fun findDiaryList(exhId: Long, userId: Long): List<SoloVisitDiaryListResult> {
        return queryRepository.findDiaryList(exhId, userId)
    }
}