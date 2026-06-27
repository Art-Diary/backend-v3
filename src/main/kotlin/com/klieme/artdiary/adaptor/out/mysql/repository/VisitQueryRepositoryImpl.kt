package com.klieme.artdiary.adaptor.out.mysql.repository

import com.klieme.artdiary.adaptor.out.mysql.entity.QExhEntity.exhEntity
import com.klieme.artdiary.adaptor.out.mysql.entity.QVisitEntity.visitEntity
import com.klieme.artdiary.application.dto.*
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class VisitQueryRepositoryImpl(
    private val queryFactory: JPAQueryFactory,
) : VisitQueryRepository {

    override fun findVisitedExhList(userId: Long): List<SoloVisitedExhResult> {

        return queryFactory
            .select(visitedExhProjection())
            .from(visitEntity)
            .join(visitEntity.exh, exhEntity)
            .where(visitEntity.user.userId.eq(userId))
            .orderBy(visitEntity.visitDate.desc())
            .fetch()
    }

    private fun visitedExhProjection(): QSoloVisitedExhResult {

        return QSoloVisitedExhResult(
            visitEntity.id,
            visitEntity.visitDate,
            exhEntity.exhId,
            exhEntity.exhName,
            exhEntity.gallery,
            exhEntity.poster
        )
    }
}