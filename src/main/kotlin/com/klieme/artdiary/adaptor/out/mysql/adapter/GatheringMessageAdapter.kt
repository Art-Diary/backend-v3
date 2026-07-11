package com.klieme.artdiary.adaptor.out.mysql.adapter

import com.klieme.artdiary.adaptor.out.mysql.entity.GatheringMessageEntity
import com.klieme.artdiary.adaptor.out.mysql.repository.GatheringMemberJpaRepository
import com.klieme.artdiary.adaptor.out.mysql.repository.GatheringMessageJpaRepository
import com.klieme.artdiary.adaptor.out.mysql.repository.GatheringMessageQueryRepository
import com.klieme.artdiary.adaptor.out.mysql.repository.GatheringQuestionJpaRepository
import com.klieme.artdiary.application.dto.GatheringMessageResult
import com.klieme.artdiary.application.port.out.GatheringMessagePort
import com.klieme.artdiary.common.exception.ArtdiaryException
import com.klieme.artdiary.common.exception.ErrorType
import org.springframework.stereotype.Component

@Component
class GatheringMessageAdapter(
    private val queryRepository: GatheringMessageQueryRepository,
    private val jpaRepository: GatheringMessageJpaRepository,
    private val gatheringQuestionJpaRepository: GatheringQuestionJpaRepository,
    private val gatheringMemberJpaRepository: GatheringMemberJpaRepository
) : GatheringMessagePort {
    override fun findList(questionId: Long): List<GatheringMessageResult> {
        return queryRepository.findList(questionId)
    }

    override fun save(questionId: Long, memberId: Long, content: String) {
        val gatheringQuestion = gatheringQuestionJpaRepository.getReferenceById(questionId)
        val gatheringMember = gatheringMemberJpaRepository.getReferenceById(memberId)

        jpaRepository.save(
            GatheringMessageEntity(
                gatheringQuestion = gatheringQuestion,
                gatheringMember = gatheringMember,
                content = content
            )
        )
    }

    override fun update(id: Long, userId: Long, content: String) {
        val entity = queryRepository.findByIdAndUserId(
            id = id,
            userId = userId
        ) ?: throw ArtdiaryException(ErrorType.NOT_FOUND)

        entity.update(content)
    }

    override fun delete(id: Long, userId: Long) {
        val entity = queryRepository.findByIdAndUserId(
            id = id,
            userId = userId
        ) ?: throw ArtdiaryException(ErrorType.NOT_FOUND)

        jpaRepository.delete(entity)
    }
}
