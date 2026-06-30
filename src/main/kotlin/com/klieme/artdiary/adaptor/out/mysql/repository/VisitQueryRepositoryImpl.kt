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

    override fun findSoloVisitedExhList(userId: Long): List<VisitedExhResult> {

        return queryFactory
            .select(visitedExhProjection())
            .from(visitEntity)
            .join(visitEntity.exh, exhEntity)
            .where(visitEntity.user.userId.eq(userId))
            .orderBy(visitEntity.visitDate.desc())
            .fetch()
    }

    override fun findGatheringVisitedExhList(gatheringId: Long): List<VisitedExhResult> {
        return queryFactory
            .select(visitedExhProjection())
            .from(visitEntity)
            .join(visitEntity.exh, exhEntity)
            .where(visitEntity.gathering.id.eq(gatheringId))
            .orderBy(visitEntity.visitDate.desc())
            .fetch()
    }

    private fun visitedExhProjection(): QVisitedExhResult {

        return QVisitedExhResult(
            visitEntity.id,
            visitEntity.visitDate,
            exhEntity.exhId,
            exhEntity.exhName,
            exhEntity.gallery,
            exhEntity.poster
        )
    }
}
