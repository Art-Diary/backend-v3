package com.klieme.artdiary.application.service.query

import com.klieme.artdiary.application.dto.GatheringMessageResult
import com.klieme.artdiary.application.port.`in`.query.GatheringMessageQuery
import com.klieme.artdiary.application.port.`in`.query.GatheringMessageQueryUseCase
import com.klieme.artdiary.application.port.out.*
import com.klieme.artdiary.common.exception.ArtdiaryException
import com.klieme.artdiary.common.exception.ErrorType
import org.springframework.stereotype.Service

@Service
class GatheringMessageQueryService(
    private val gatheringMemberPort: GatheringMemberPort,
    private val visitPort: VisitPort,
    private val gatheringQuestionPort: GatheringQuestionPort,
    private val gatheringMessagePort: GatheringMessagePort
) : GatheringMessageQueryUseCase {
    override fun getList(query: GatheringMessageQuery): List<GatheringMessageResult> {
        if (!gatheringMemberPort.existsByUserIdAndGatheringId(
                userId = query.userId,
                gatheringId = query.gatheringId
            )
        ) {
            throw ArtdiaryException(ErrorType.NOT_FOUND)
        }

        if (!visitPort.existsByIdAndGatheringId(
                gatheringId = query.gatheringId,
                id = query.visitId
            )
        ) {
            throw ArtdiaryException(ErrorType.NOT_FOUND)
        }

        if (!gatheringQuestionPort.existsByIdAndVisitId(
                id = query.questionId,
                visitId = query.visitId
            )
        ) {
            throw ArtdiaryException(ErrorType.NOT_FOUND)
        }

        return gatheringMessagePort.findList(query.questionId)
    }
}
