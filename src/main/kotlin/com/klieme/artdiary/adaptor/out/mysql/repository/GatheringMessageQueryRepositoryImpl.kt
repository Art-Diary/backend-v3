package com.klieme.artdiary.adaptor.out.mysql.repository

import com.klieme.artdiary.adaptor.out.mysql.entity.QGatheringMemberEntity.gatheringMemberEntity
import com.klieme.artdiary.adaptor.out.mysql.entity.QGatheringMessageEntity.gatheringMessageEntity
import com.klieme.artdiary.adaptor.out.mysql.entity.QUserEntity.userEntity
import com.klieme.artdiary.application.dto.GatheringMessageResult
import com.klieme.artdiary.application.dto.QGatheringMessageResult
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class GatheringMessageQueryRepositoryImpl(
    private val queryFactory: JPAQueryFactory,
) : GatheringMessageQueryRepository {
    override fun findList(questionId: Long): List<GatheringMessageResult> {
        return queryFactory
            .select(messageProjection())
            .from(gatheringMessageEntity)
            .join(gatheringMessageEntity.gatheringMember, gatheringMemberEntity)
            .join(gatheringMemberEntity.user, userEntity)
            .where(
                gatheringMessageEntity.gatheringQuestion.id.eq(questionId)
            )
            .orderBy(gatheringMessageEntity.createdAt.asc())
            .fetch()
    }

    private fun messageProjection(): QGatheringMessageResult {
        return QGatheringMessageResult(
            gatheringMessageEntity.id,
            gatheringMessageEntity.content,
            gatheringMessageEntity.createdAt,
            userEntity.userId,
            userEntity.nickname,
            userEntity.profile
        )
    }
}
