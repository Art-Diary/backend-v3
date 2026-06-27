package com.klieme.artdiary.adaptor.out.mysql.adapter

import com.klieme.artdiary.adaptor.out.mysql.entity.SoloDiaryEntity
import com.klieme.artdiary.adaptor.out.mysql.repository.*
import com.klieme.artdiary.application.dto.ExhReviewResult
import com.klieme.artdiary.application.dto.SoloDiaryResult
import com.klieme.artdiary.application.port.out.SoloDiaryPort
import com.klieme.artdiary.common.exception.ArtdiaryException
import com.klieme.artdiary.common.exception.ErrorType
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class SoloDiaryPersistenceAdapter(
    private val jpaRepository: SoloDiaryJpaRepository,
    private val queryRepository: SoloDiaryQueryRepository,
    private val visitJpaRepository: VisitJpaRepository,
    private val userJpaRepository: UserJpaRepository,
    private val questionJpaRepository: QuestionJpaRepository
) : SoloDiaryPort {
    override fun findDiaryList(
        visitId: Long,
        userId: Long
    ): List<SoloDiaryResult> {
        return queryRepository.findDiaryList(visitId, userId)
    }

    override fun save(
        visitId: Long,
        userId: Long,
        questionId: Long,
        answerContent: String,
        writeDate: LocalDateTime,
        isPublic: Boolean
    ) {
        val user = userJpaRepository.getReferenceById(userId)
        val visitEntity = visitJpaRepository.findByIdAndUser(
            visitId,
            user
        ) ?: throw ArtdiaryException(ErrorType.NOT_FOUND)

        val questionEntity = questionJpaRepository.findById(questionId)
            .orElseThrow {
                ArtdiaryException(ErrorType.NOT_FOUND)
            }

        jpaRepository.save(
            SoloDiaryEntity(
                visit = visitEntity,
                question = questionEntity,
                content = answerContent,
                writeDate = writeDate,
                isPublic = isPublic
            )
        )
    }

    override fun update(
        visitId: Long,
        userId: Long,
        soloDiaryId: Long,
        questionId: Long,
        answerContent: String,
        writeDate: LocalDateTime,
        isPublic: Boolean
    ) {
        val entity = queryRepository.findDiary(
            visitId = visitId,
            userId = userId,
            soloDiaryId = soloDiaryId
        ) ?: throw ArtdiaryException(ErrorType.NOT_FOUND)

        entity.update(
            question = questionJpaRepository.getReferenceById(questionId),
            answerContent = answerContent,
            writeDate = writeDate,
            isPublic = isPublic
        )
    }

    override fun delete(
        visitId: Long,
        userId: Long,
        soloDiaryId: Long
    ) {
        val entity = queryRepository.findDiary(
            visitId = visitId,
            userId = userId,
            soloDiaryId = soloDiaryId
        ) ?: throw ArtdiaryException(ErrorType.NOT_FOUND)

        jpaRepository.delete(entity)
    }

    override fun findExhReviewList(exhId: Long): List<ExhReviewResult> {
        return queryRepository.findExhReviewList(
            exhId = exhId
        )
    }
}