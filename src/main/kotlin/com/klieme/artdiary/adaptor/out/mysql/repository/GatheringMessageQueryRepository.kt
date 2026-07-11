package com.klieme.artdiary.adaptor.out.mysql.repository

import com.klieme.artdiary.adaptor.out.mysql.entity.GatheringMessageEntity
import com.klieme.artdiary.application.dto.GatheringMessageResult

interface GatheringMessageQueryRepository {
    fun findList(questionId: Long): List<GatheringMessageResult>

    fun findByIdAndUserId(
        id: Long,
        userId: Long
    ): GatheringMessageEntity?
}
