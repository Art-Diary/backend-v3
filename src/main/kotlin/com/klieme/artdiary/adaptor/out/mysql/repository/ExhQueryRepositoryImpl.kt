package com.klieme.artdiary.adaptor.out.mysql.repository

import com.klieme.artdiary.adaptor.out.mysql.entity.QExhEntity.exhEntity
import com.klieme.artdiary.adaptor.out.mysql.entity.QLikeExhEntity.likeExhEntity
import com.klieme.artdiary.adaptor.out.mysql.entity.QVisitEntity.visitEntity
import com.klieme.artdiary.application.dto.ExhDetailResult
import com.klieme.artdiary.application.dto.ExhListResult
import com.klieme.artdiary.application.dto.QExhDetailResult
import com.klieme.artdiary.application.dto.QExhListResult
import com.querydsl.core.types.OrderSpecifier
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.core.types.dsl.CaseBuilder
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.Slice
import org.springframework.data.domain.SliceImpl
import org.springframework.stereotype.Repository
import org.springframework.data.domain.Pageable
import java.time.LocalDate

@Repository
class ExhQueryRepositoryImpl(
    private val queryFactory: JPAQueryFactory,
) : ExhQueryRepository {

    override fun findList(
        keyword: String?,
        date: LocalDate?,
        userId: Long,
        pageable: Pageable,
    ): Slice<ExhListResult> {

        val contents = queryFactory
            .select(listProjection())
            .from(exhEntity)
            .leftJoin(likeExhEntity)
            .on(
                likeExhEntity.exhId.eq(exhEntity.exhId),
                likeExhEntity.userId.eq(userId)
            )
            .where(
                keywordCondition(keyword),
                dateCondition(date)
            )
            .orderBy(
                currentExhibitionOrder(date),
                exhEntity.endDate.asc(),
                exhEntity.likeCount.desc(),
                exhEntity.exhName.asc(),
            )
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong() + 1)
            .fetch()

        val hasNext = contents.size > pageable.pageSize

        val result =
            if (hasNext) {
                contents.subList(0, pageable.pageSize)
            } else {
                contents
            }

        return SliceImpl(
            result,
            pageable,
            hasNext
        )
    }

    private fun keywordCondition(
        keyword: String?,
    ): BooleanExpression? {

        if (keyword.isNullOrBlank()) {
            return null
        }

        return exhEntity.exhName.containsIgnoreCase(keyword)
            .or(exhEntity.gallery.containsIgnoreCase(keyword))
            .or(exhEntity.painter.containsIgnoreCase(keyword))
    }

    private fun dateCondition(
        date: LocalDate?,
    ): BooleanExpression? {

        if (date == null) {
            return null
        }

        return exhEntity.startDate.loe(date)
            .and(
                exhEntity.endDate.goe(date)
            )
    }

    private fun currentExhibitionOrder(
        date: LocalDate?,
    ): OrderSpecifier<Int> {

        val targetDate = date ?: LocalDate.now()

        return CaseBuilder()
            .`when`(
                exhEntity.startDate.loe(targetDate)
                    .and(exhEntity.endDate.goe(targetDate))
            )
            .then(0)
            .otherwise(1)
            .asc()
    }

    private fun listProjection(): QExhListResult {

        return QExhListResult(
            exhEntity.exhId,
            exhEntity.exhName,
            exhEntity.gallery,
            exhEntity.startDate,
            exhEntity.endDate,
            exhEntity.painter,
            exhEntity.poster,
            likeExhEntity.id.isNotNull
        )
    }

    override fun findDetail(exhId: Long, userId: Long): ExhDetailResult? {

        return queryFactory
            .select(detailProjection())
            .from(exhEntity)
            .leftJoin(likeExhEntity)
            .on(
                likeExhEntity.exhId.eq(exhEntity.exhId),
                likeExhEntity.userId.eq(userId)
            )
            .leftJoin(visitEntity)
            .on(
                visitEntity.exh.eq(exhEntity),
                visitEntity.user.userId.eq(userId)
            )
            .where(
                exhEntity.exhId.eq(exhId)
            )
            .fetchOne()
    }

    private fun detailProjection(): QExhDetailResult {

        return QExhDetailResult(
            exhEntity.exhId,
            exhEntity.exhName,
            exhEntity.gallery,
            exhEntity.startDate,
            exhEntity.endDate,
            exhEntity.painter,
            exhEntity.fee,
            exhEntity.intro,
            exhEntity.homepageLink,
            exhEntity.poster,
            exhEntity.artField,
            exhEntity.source,
            likeExhEntity.id.isNotNull,
            visitEntity.isNotNull
        )
    }
}