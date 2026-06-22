package com.klieme.artdiary.adaptor.out.mysql

import com.klieme.artdiary.application.port.out.ExhPort
import com.klieme.artdiary.common.exception.ArtdiaryException
import com.klieme.artdiary.common.exception.ErrorType
import com.klieme.artdiary.domain.Exh
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class ExhPersistenceAdapter(
    private val jpaRepository: ExhJpaRepository
) : ExhPort {
    override fun findByKeyword(keyword: String): List<Exh> {
        val exhList =
            jpaRepository.findByExhNameContainingIgnoreCaseOrGalleryContainingIgnoreCaseOrPainterContainingIgnoreCaseOrderByExhNameAsc(
                keyword,
                keyword,
                keyword
            )

        return exhList.map { ExhMapper.toDomain(it) }
    }

    override fun findByDate(date: LocalDate): List<Exh> {
        val exhList = jpaRepository.findByStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByExhNameAsc(date, date)

        return exhList.map { ExhMapper.toDomain(it) }
    }

    override fun findAll(): List<Exh> {
        val exhList = jpaRepository.findAll()

        return exhList.map { ExhMapper.toDomain(it) }
    }

    override fun findByExhId(exhId: Long): Exh {
        val exh = jpaRepository.findByExhId(exhId)
            ?: throw ArtdiaryException(
                ErrorType.NOT_FOUND,
                "Id [$exhId] has no exhibition data"
            )
        return ExhMapper.toDomain(exh)
    }
}
