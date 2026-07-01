package com.klieme.artdiary.adaptor.out.mysql.adapter

import com.klieme.artdiary.adaptor.out.mysql.entity.GatheringQuestionEntity
import com.klieme.artdiary.adaptor.out.mysql.mapper.GatheringQuestionMapper
import com.klieme.artdiary.adaptor.out.mysql.repository.GatheringQuestionJpaRepository
import com.klieme.artdiary.adaptor.out.mysql.repository.VisitJpaRepository
import com.klieme.artdiary.application.port.out.GatheringQuestionPort
import com.klieme.artdiary.common.exception.ArtdiaryException
import com.klieme.artdiary.common.exception.ErrorType
import com.klieme.artdiary.domain.GatheringQuestion
import org.springframework.stereotype.Component

@Component
class GatheringQuestionPersistenceAdapter(
    private val jpaRepository: GatheringQuestionJpaRepository,
    private val visitJpaRepository: VisitJpaRepository
) : GatheringQuestionPort {
    override fun findList(visitId: Long): List<GatheringQuestion> {
        return jpaRepository.findByVisit(
            visit = visitJpaRepository.getReferenceById(visitId)
        ).map { GatheringQuestionMapper.toDomain(it) }
    }

    override fun save(visitId: Long, content: String) {
        jpaRepository.save(
            GatheringQuestionEntity(
                visit = visitJpaRepository.getReferenceById(visitId),
                content = content
            )
        )
    }

    override fun update(
        id: Long,
        visitId: Long,
        content: String
    ) {
        val entity = jpaRepository.findByIdAndVisit(
            id = id,
            visit = visitJpaRepository.getReferenceById(visitId)
        ) ?: throw ArtdiaryException(ErrorType.NOT_FOUND)

        entity.update(content)
    }
}
