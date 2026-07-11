package com.klieme.artdiary.adaptor.out.mysql.adapter

import com.klieme.artdiary.adaptor.out.mysql.repository.ExhJpaRepository
import com.klieme.artdiary.adaptor.out.mysql.repository.ExhQueryRepository
import com.klieme.artdiary.application.dto.ExhDetailResult
import com.klieme.artdiary.application.dto.ExhListResult
import com.klieme.artdiary.application.port.out.ExhPort
import com.klieme.artdiary.common.exception.ArtdiaryException
import com.klieme.artdiary.common.exception.ErrorType
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class ExhPersistenceAdapter(
    private val jpaRepository: ExhJpaRepository,
    private val queryRepository: ExhQueryRepository
) : ExhPort {
    override fun existsByExhId(exhId: Long): Boolean {
        return jpaRepository.existsByExhId(exhId)
    }

    override fun increaseLikeCount(exhId: Long) {
        val updatedCount = jpaRepository.increaseLikeCount(exhId)

        if (updatedCount == 0) {
            throw ArtdiaryException(ErrorType.NOT_FOUND)
        }
    }

    override fun decreaseLikeCount(exhId: Long) {
        jpaRepository.decreaseLikeCount(exhId)
    }

    override fun findList(
        keyword: String?,
        date: LocalDate?,
        userId: Long,
        pageable: Pageable,
    ): Slice<ExhListResult> {
        return queryRepository.findList(
            keyword = keyword,
            date = date,
            userId = userId,
            pageable = pageable,
        )
    }

    override fun findDetail(exhId: Long, userId: Long): ExhDetailResult {
        return queryRepository.findDetail(exhId = exhId, userId = userId)
            ?: throw ArtdiaryException(ErrorType.NOT_FOUND)
    }
}
