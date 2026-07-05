package com.klieme.artdiary.adaptor.out.mysql.repository

import com.klieme.artdiary.adaptor.out.mysql.entity.QExhEntity.exhEntity
import com.klieme.artdiary.adaptor.out.mysql.entity.QVisitEntity.visitEntity
import com.klieme.artdiary.application.dto.*
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository
import java.time.LocalDate

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

    override fun findCalendarData(userId: Long, year: Int, month: Int): List<CalendarVisitResult> {
        val start = LocalDate.of(year, month, 1)
        val end = start.plusMonths(1)

        return queryFactory
            .select(calendarProjection())
            .from(visitEntity)
            .join(visitEntity.exh, exhEntity)
            .where(
                visitEntity.user.userId.eq(userId),
                visitEntity.visitDate.goe(start),
                visitEntity.visitDate.lt(end)
            )
            .orderBy(
                visitEntity.visitDate.asc(),
                visitEntity.id.asc()
            )
            .fetch()
    }

    private fun calendarProjection(): QCalendarVisitResult {
        return QCalendarVisitResult(
            visitEntity.id,
            visitEntity.visitDate,
            exhEntity.exhId,
            exhEntity.exhName,
            exhEntity.poster
        )
    }
}
