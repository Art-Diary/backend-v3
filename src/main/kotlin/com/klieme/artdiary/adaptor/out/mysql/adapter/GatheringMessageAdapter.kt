package com.klieme.artdiary.adaptor.out.mysql.adapter

import com.klieme.artdiary.adaptor.out.mysql.repository.GatheringMessageQueryRepository
import com.klieme.artdiary.application.dto.GatheringMessageResult
import com.klieme.artdiary.application.port.out.GatheringMessagePort
import org.springframework.stereotype.Component

@Component
class GatheringMessageAdapter(
    private val queryRepository: GatheringMessageQueryRepository
) : GatheringMessagePort {
    override fun findList(questionId: Long): List<GatheringMessageResult> {
        return queryRepository.findList(questionId)
    }
}
