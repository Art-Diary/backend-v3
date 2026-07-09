package com.klieme.artdiary.adaptor.out.mysql.repository

import com.klieme.artdiary.adaptor.out.mysql.entity.QExhEntity.exhEntity
import com.klieme.artdiary.adaptor.out.mysql.entity.QSoloDiaryEntity.soloDiaryEntity
import com.klieme.artdiary.adaptor.out.mysql.entity.QVisitEntity.visitEntity
import com.klieme.artdiary.application.dto.*
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
class VisitQueryRepositoryImpl(
    private val queryFactory: JPAQueryFactory,
) : VisitQueryRepository {

    override fun findSoloVisitedExhList(userId: Long): List<SoloVisitedExhResult> {

        return queryFactory
            .select(soloVisitedExhProjection())
            .from(visitEntity)
            .join(visitEntity.exh, exhEntity)
            .leftJoin(soloDiaryEntity)
            .on(
                soloDiaryEntity.visit.eq(visitEntity)
            )
            .where(visitEntity.user.userId.eq(userId))
            .orderBy(visitEntity.visitDate.desc())
            .fetch()
    }

    private fun soloVisitedExhProjection(): QSoloVisitedExhResult {

        return QSoloVisitedExhResult(
            visitEntity.id,
            visitEntity.visitDate,
            exhEntity.exhId,
            exhEntity.exhName,
            exhEntity.gallery,
            exhEntity.poster,
            soloDiaryEntity.isNotNull
        )
    }

    override fun findGatheringVisitedExhList(gatheringId: Long): List<GatheringVisitedExhResult> {
        return queryFactory
            .select(gatheringVisitedExhProjection())
            .from(visitEntity)
            .join(visitEntity.exh, exhEntity)
            .where(visitEntity.gathering.id.eq(gatheringId))
            .orderBy(visitEntity.visitDate.desc())
            .fetch()
    }

    private fun gatheringVisitedExhProjection(): QGatheringVisitedExhResult {

        return QGatheringVisitedExhResult(
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
