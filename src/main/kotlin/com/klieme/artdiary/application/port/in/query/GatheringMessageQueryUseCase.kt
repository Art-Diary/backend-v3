package com.klieme.artdiary.application.port.`in`.query

import com.klieme.artdiary.application.dto.GatheringMessageResult

interface GatheringMessageQueryUseCase {
    fun getList(query: GatheringMessageQuery): List<GatheringMessageResult>
}
