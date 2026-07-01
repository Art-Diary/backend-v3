package com.klieme.artdiary.adaptor.out.mysql.adapter

import com.klieme.artdiary.adaptor.out.mysql.entity.VisitEntity
import com.klieme.artdiary.adaptor.out.mysql.repository.*
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
    private val userJpaRepository: UserJpaRepository,
    private val gatheringJpaRepository: GatheringJpaRepository
) : VisitPort {
    override fun findSoloVisitedExhList(userId: Long): List<VisitedExhResult> {
        return queryRepository.findSoloVisitedExhList(userId)
    }

    override fun saveSoloExh(exhId: Long, userId: Long, visitDate: LocalDate) {
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

    override fun existsAvailableVisitDate(
        exhId: Long,
        visitDate: LocalDate
    ): Boolean {
        return exhJpaRepository.existsByExhIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            exhId = exhId,
            visitDate = visitDate,
            visitDate2 = visitDate
        )
    }

    override fun existsByExhIdAndGatheringIdAndVisitDate(
        gatheringId: Long,
        exhId: Long,
        visitDate: LocalDate
    ): Boolean {
        val exh = exhJpaRepository.getReferenceById(exhId)
        val gathering = gatheringJpaRepository.getReferenceById(gatheringId)

        return jpaRepository.existsByExhAndGatheringAndVisitDate(
            exh = exh,
            gathering = gathering,
            visitDate = visitDate
        )
    }

    override fun saveGatheringExh(
        exhId: Long,
        gatheringId: Long,
        visitDate: LocalDate
    ) {
        jpaRepository.save(
            VisitEntity(
                exh = exhJpaRepository.getReferenceById(exhId),
                gathering = gatheringJpaRepository.getReferenceById(gatheringId),
                visitDate = visitDate
            )
        )
    }
}
