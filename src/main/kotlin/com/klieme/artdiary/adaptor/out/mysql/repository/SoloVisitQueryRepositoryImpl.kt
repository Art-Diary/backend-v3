package com.klieme.artdiary.adaptor.out.mysql.repository

import com.klieme.artdiary.adaptor.out.mysql.entity.QExhEntity.exhEntity
import com.klieme.artdiary.adaptor.out.mysql.entity.QQuestionEntity.questionEntity
import com.klieme.artdiary.adaptor.out.mysql.entity.QSoloDiaryEntity.soloDiaryEntity
import com.klieme.artdiary.adaptor.out.mysql.entity.QTicketEntity.ticketEntity
import com.klieme.artdiary.adaptor.out.mysql.entity.QVisitEntity.visitEntity
import com.klieme.artdiary.application.dto.*
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class SoloVisitQueryRepositoryImpl(
    private val queryFactory: JPAQueryFactory,
) : SoloVisitQueryRepository {

    override fun findTicketList(userId: Long): List<SoloVisitTicketListResult> {

        return queryFactory
            .select(ticketListProjection())
            .from(ticketEntity)
            .join(ticketEntity.exh, exhEntity)
            .join(visitEntity).on(visitEntity.ticket.eq(ticketEntity))
            .where(ticketEntity.user.userId.eq(userId))
            .groupBy(
                exhEntity.exhId,
                exhEntity.exhName,
                exhEntity.gallery,
                exhEntity.poster
            )
            .orderBy(visitEntity.visitDate.max().desc())
            .fetch()
    }

    private fun ticketListProjection(): QSoloVisitTicketListResult {

        return QSoloVisitTicketListResult(
            exhEntity.exhId,
            exhEntity.exhName,
            exhEntity.gallery,
            exhEntity.poster,
            visitEntity.visitDate.max()
        )
    }

    override fun findDiaryList(exhId: Long, userId: Long): List<SoloVisitDiaryListResult> {
        return queryFactory
            .select(diaryListProjection())
            .from(soloDiaryEntity)
            .join(soloDiaryEntity.visit, visitEntity)
            .join(visitEntity.ticket, ticketEntity)
            .join(soloDiaryEntity.question, questionEntity)
            .where(
                ticketEntity.user.userId.eq(userId),
                ticketEntity.exh.exhId.eq(exhId)
            )
            .orderBy(
                visitEntity.visitDate.desc(),
                questionEntity.id.asc()
            )
            .fetch()
    }

    private fun diaryListProjection(): QSoloVisitDiaryListResult {

        return QSoloVisitDiaryListResult(
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