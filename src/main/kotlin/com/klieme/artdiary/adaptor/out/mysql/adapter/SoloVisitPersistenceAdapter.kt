package com.klieme.artdiary.adaptor.out.mysql.adapter

import com.klieme.artdiary.adaptor.out.mysql.repository.SoloVisitQueryRepository
import com.klieme.artdiary.application.dto.SoloDiaryListResult
import com.klieme.artdiary.application.dto.SoloVisitedExhListResult
import com.klieme.artdiary.application.port.out.SoloVisitPort
import org.springframework.stereotype.Component

@Component
class SoloVisitPersistenceAdapter(
    private val queryRepository: SoloVisitQueryRepository
) : SoloVisitPort {
    override fun findVisitedExhList(userId: Long): List<SoloVisitedExhListResult> {
        return queryRepository.findVisitedExhList(userId)
    }

    override fun findDiaryList(exhId: Long, userId: Long): List<SoloDiaryListResult> {
        return queryRepository.findDiaryList(exhId, userId)
    }
}