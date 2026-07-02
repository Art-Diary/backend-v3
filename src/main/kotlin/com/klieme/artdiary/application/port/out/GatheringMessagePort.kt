package com.klieme.artdiary.application.port.out

import com.klieme.artdiary.application.dto.GatheringMessageResult

interface GatheringMessagePort {
    fun findList(
        questionId: Long
    ): List<GatheringMessageResult>
}
