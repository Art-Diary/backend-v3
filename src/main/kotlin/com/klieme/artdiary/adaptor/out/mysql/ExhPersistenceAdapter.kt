package com.klieme.artdiary.adaptor.out.mysql

import com.klieme.artdiary.application.port.out.ExhPort
import com.klieme.artdiary.common.exception.ArtdiaryException
import com.klieme.artdiary.common.exception.ErrorType
import org.springframework.stereotype.Component

@Component
class ExhPersistenceAdapter(
    private val jpaRepository: ExhJpaRepository
): ExhPort {
    override fun findByExhId(exhId: Long): ExhEntity {
        return jpaRepository.findByExhId(exhId).orElseThrow{
            ArtdiaryException(
                ErrorType.NOT_FOUND,
                "Id [$exhId] has no exhibition data"
            )
        }
    }
}
