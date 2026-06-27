package com.klieme.artdiary.adaptor.out.mysql.adapter

import com.klieme.artdiary.adaptor.out.mysql.repository.VisitQueryRepository
import com.klieme.artdiary.application.dto.SoloVisitedExhResult
import com.klieme.artdiary.application.port.out.VisitPort
import org.springframework.stereotype.Component

@Component
class VisitPersistenceAdapter(
    private val queryRepository: VisitQueryRepository,
) : VisitPort {
    override fun findVisitedExhList(userId: Long): List<SoloVisitedExhResult> {
        return queryRepository.findVisitedExhList(userId)
    }
}