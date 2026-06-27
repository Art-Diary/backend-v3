package com.klieme.artdiary.adaptor.out.mysql.repository

import com.klieme.artdiary.adaptor.out.mysql.entity.QQuestionEntity.questionEntity
import com.klieme.artdiary.adaptor.out.mysql.entity.QSoloDiaryEntity.soloDiaryEntity
import com.klieme.artdiary.adaptor.out.mysql.entity.QUserEntity.userEntity
import com.klieme.artdiary.adaptor.out.mysql.entity.QVisitEntity.visitEntity
import com.klieme.artdiary.adaptor.out.mysql.entity.SoloDiaryEntity
import com.klieme.artdiary.application.dto.*
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class SoloDiaryQueryRepositoryImpl(
    private val queryFactory: JPAQueryFactory,
) : SoloDiaryQueryRepository {
    override fun findDiaryList(
        visitId: Long,
        userId: Long
    ): List<SoloDiaryResult> {
        return queryFactory
            .select(diaryProjection())
            .from(soloDiaryEntity)
            .join(soloDiaryEntity.visit, visitEntity)
            .join(soloDiaryEntity.question, questionEntity)
            .where(
                visitEntity.id.eq(visitId),
                visitEntity.user.userId.eq(userId)
            )
            .orderBy(
                soloDiaryEntity.writeDate.desc()
            )
            .fetch()
    }

    private fun diaryProjection(): QSoloDiaryResult {

        return QSoloDiaryResult(
            soloDiaryEntity.id,
            questionEntity.id,
            questionEntity.content,
            soloDiaryEntity.content,
            soloDiaryEntity.writeDate,
            soloDiaryEntity.isPublic
        )
    }

    override fun findDiary(
        visitId: Long,
        userId: Long,
        soloDiaryId: Long
    ): SoloDiaryEntity? {
        return queryFactory
            .selectFrom(soloDiaryEntity)
            .join(soloDiaryEntity.visit, visitEntity)
            .where(
                soloDiaryEntity.id.eq(soloDiaryId),
                visitEntity.user.userId.eq(userId),
                visitEntity.id.eq(visitId)
            )
            .fetchOne()
    }

    override fun findExhReviewList(exhId: Long): List<ExhReviewResult> {
        return queryFactory
            .select(reviewProjection())
            .from(soloDiaryEntity)
            .join(soloDiaryEntity.visit, visitEntity)
            .join(soloDiaryEntity.question, questionEntity)
            .join(visitEntity.user, userEntity)
            .where(
                soloDiaryEntity.visit.exh.exhId.eq(exhId),
                soloDiaryEntity.isPublic.isTrue
            )
            .orderBy(soloDiaryEntity.writeDate.desc())
            .fetch()
    }

    private fun reviewProjection(): QExhReviewResult {

        return QExhReviewResult(
            soloDiaryEntity.id,
            questionEntity.content,
            soloDiaryEntity.content,
            soloDiaryEntity.writeDate,
            userEntity.userId,
            userEntity.nickname,
            userEntity.profile
        )
    }
}