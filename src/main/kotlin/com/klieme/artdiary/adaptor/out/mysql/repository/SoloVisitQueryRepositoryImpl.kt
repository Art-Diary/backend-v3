package com.klieme.artdiary.adaptor.out.mysql.repository

import com.klieme.artdiary.adaptor.out.mysql.entity.QExhEntity.exhEntity
import com.klieme.artdiary.adaptor.out.mysql.entity.QQuestionEntity.questionEntity
import com.klieme.artdiary.adaptor.out.mysql.entity.QSoloDiaryEntity.soloDiaryEntity
import com.klieme.artdiary.adaptor.out.mysql.entity.QVisitEntity.visitEntity
import com.klieme.artdiary.application.dto.*
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class SoloVisitQueryRepositoryImpl(
    private val queryFactory: JPAQueryFactory,
) : SoloVisitQueryRepository {

    override fun findVisitedExhList(userId: Long): List<SoloVisitedExhListResult> {

        return queryFactory
            .select(visitedExhListProjection())
            .from(visitEntity)
            .join(visitEntity.exh, exhEntity)
            .where(visitEntity.user.userId.eq(userId))
            .groupBy(
                exhEntity.exhId,
                exhEntity.exhName,
                exhEntity.gallery,
                exhEntity.poster
            )
            .orderBy(visitEntity.visitDate.max().desc())
            .fetch()
    }

    private fun visitedExhListProjection(): QSoloVisitedExhListResult {

        return QSoloVisitedExhListResult(
            exhEntity.exhId,
            exhEntity.exhName,
            exhEntity.gallery,
            exhEntity.poster,
            visitEntity.visitDate.max()
        )
    }

    override fun findDiaryList(exhId: Long, userId: Long): List<SoloDiaryListResult> {
        return queryFactory
            .select(diaryListProjection())
            .from(soloDiaryEntity)
            .join(soloDiaryEntity.visit, visitEntity)
            .join(soloDiaryEntity.question, questionEntity)
            .where(
                visitEntity.user.userId.eq(userId),
                visitEntity.exh.exhId.eq(exhId)
            )
            .orderBy(
                visitEntity.visitDate.desc(),
                questionEntity.id.asc()
            )
            .fetch()
    }

    private fun diaryListProjection(): QSoloDiaryListResult {

        return QSoloDiaryListResult(
            visitEntity.id,
            visitEntity.visitDate,
            soloDiaryEntity.id,
            questionEntity.id,
            questionEntity.content,
            soloDiaryEntity.content,
            soloDiaryEntity.writeDate,
            soloDiaryEntity.isPublic
        )
    }
}