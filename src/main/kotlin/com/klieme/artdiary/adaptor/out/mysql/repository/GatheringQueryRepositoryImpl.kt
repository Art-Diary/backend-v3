package com.klieme.artdiary.adaptor.out.mysql.repository

import com.klieme.artdiary.adaptor.out.mysql.entity.QGatheringEntity.gatheringEntity
import com.klieme.artdiary.adaptor.out.mysql.entity.QGatheringMemberEntity
import com.klieme.artdiary.adaptor.out.mysql.entity.QGatheringMemberEntity.gatheringMemberEntity
import com.klieme.artdiary.adaptor.out.mysql.entity.QUserEntity.userEntity
import com.klieme.artdiary.adaptor.out.mysql.entity.QVisitEntity.visitEntity
import com.klieme.artdiary.application.dto.GatheringListResult
import com.klieme.artdiary.application.dto.GatheringMemberResult
import com.klieme.artdiary.application.dto.QGatheringListResult
import com.klieme.artdiary.application.dto.QGatheringMemberResult
import com.querydsl.jpa.JPAExpressions
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class GatheringQueryRepositoryImpl(
    private val queryFactory: JPAQueryFactory,
) : GatheringQueryRepository {
    override fun findList(userId: Long): List<GatheringListResult> {
        val myMember = QGatheringMemberEntity("myMember")

        return queryFactory
            .select(listProjection())
            .from(gatheringEntity)
            .leftJoin(gatheringMemberEntity)
            .on(gatheringMemberEntity.gathering.eq(gatheringEntity))
            .leftJoin(visitEntity)
            .on(visitEntity.gathering.eq(gatheringEntity))
            .where(
                gatheringEntity.id.`in`(
                    JPAExpressions
                        .select(myMember.gathering.id)
                        .from(myMember)
                        .where(myMember.user.userId.eq(userId))
                )
            )
            .groupBy(
                gatheringEntity.id,
                gatheringEntity.name,
                gatheringMemberEntity.user.userId,
                gatheringMemberEntity.user.nickname,
                gatheringMemberEntity.user.profile
            )
            .orderBy(gatheringEntity.name.asc())
            .fetch()
    }

    private fun listProjection(): QGatheringListResult {
        return QGatheringListResult(
            gatheringEntity.id,
            gatheringEntity.name,
            visitEntity.id.countDistinct(),
            gatheringMemberEntity.user.userId,
            gatheringMemberEntity.user.nickname,
            gatheringMemberEntity.user.profile
        )
    }

    override fun findMemberList(gatheringId: Long, userId: Long): List<GatheringMemberResult> {
        val member = gatheringMemberEntity
        val myMember = QGatheringMemberEntity("myMember")

        return queryFactory
            .select(memberProjection())
            .from(member)
            .join(member.user, userEntity)
            .where(
                member.gathering.id.eq(gatheringId),
                JPAExpressions
                    .selectOne()
                    .from(myMember)
                    .where(
                        myMember.gathering.id.eq(gatheringId),
                        myMember.user.userId.eq(userId)
                    )
                    .exists()
            )
            .orderBy(member.user.nickname.asc())
            .fetch()
    }

    private fun memberProjection(): QGatheringMemberResult {
        return QGatheringMemberResult(
            userEntity.userId,
            userEntity.nickname,
            userEntity.profile
        )
    }
}
