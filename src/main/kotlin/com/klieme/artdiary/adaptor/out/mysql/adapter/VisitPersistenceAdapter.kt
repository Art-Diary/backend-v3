package com.klieme.artdiary.adaptor.out.mysql.adapter

import com.klieme.artdiary.adaptor.out.mysql.entity.VisitEntity
import com.klieme.artdiary.adaptor.out.mysql.repository.ExhJpaRepository
import com.klieme.artdiary.adaptor.out.mysql.repository.UserJpaRepository
import com.klieme.artdiary.adaptor.out.mysql.repository.VisitJpaRepository
import com.klieme.artdiary.adaptor.out.mysql.repository.VisitQueryRepository
import com.klieme.artdiary.application.dto.VisitedExhResult
import com.klieme.artdiary.application.port.out.VisitPort
import com.klieme.artdiary.common.exception.ArtdiaryException
import com.klieme.artdiary.common.exception.ErrorType
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class VisitPersistenceAdapter(
    private val queryRepository: VisitQueryRepository,
    private val jpaRepository: VisitJpaRepository,
    private val exhJpaRepository: ExhJpaRepository,
    private val userJpaRepository: UserJpaRepository
) : VisitPort {
    override fun findSoloVisitedExhList(userId: Long): List<VisitedExhResult> {
        return queryRepository.findSoloVisitedExhList(userId)
    }

    override fun save(exhId: Long, userId: Long, visitDate: LocalDate) {
        val exh = exhJpaRepository.getReferenceById(exhId)
        val user = userJpaRepository.getReferenceById(userId)

        if (jpaRepository.existsByExhAndUserAndVisitDate(exh, user, visitDate)) {
            throw ArtdiaryException(ErrorType.CONFLICT)
        }

        jpaRepository.save(
            VisitEntity(
                visitDate = visitDate,
                exh = exh,
                user = user
            )
        )
    }

    override fun findGatheringVisitedExhList(gatheringId: Long): List<VisitedExhResult> {
        return queryRepository.findGatheringVisitedExhList(gatheringId)
    }
}
