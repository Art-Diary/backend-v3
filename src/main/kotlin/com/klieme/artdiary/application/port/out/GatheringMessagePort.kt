package com.klieme.artdiary.application.port.out

import com.klieme.artdiary.application.dto.GatheringMessageResult

interface GatheringMessagePort {
    fun findList(
        questionId: Long
    ): List<GatheringMessageResult>

    fun save(
        questionId: Long,
        memberId: Long,
        content: String
    )

    fun update(
        id: Long,
        userId: Long,
        content: String
    )

    fun delete(
        id: Long,
        userId: Long
    )
}
