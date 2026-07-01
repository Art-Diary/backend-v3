package com.klieme.artdiary.application.service.query

import com.klieme.artdiary.application.port.`in`.query.GatheringQuestionQuery
import com.klieme.artdiary.application.port.`in`.query.GatheringQuestionQueryUseCase
import com.klieme.artdiary.application.port.out.GatheringPort
import com.klieme.artdiary.application.port.out.GatheringQuestionPort
import com.klieme.artdiary.application.port.out.VisitPort
import com.klieme.artdiary.common.exception.ArtdiaryException
import com.klieme.artdiary.common.exception.ErrorType
import com.klieme.artdiary.domain.GatheringQuestion
import org.springframework.stereotype.Service

@Service
class GatheringQuestionQueryService(
    private val gatheringPort: GatheringPort,
    private val visitPort: VisitPort,
    private val gatheringQuestionPort: GatheringQuestionPort
) : GatheringQuestionQueryUseCase {
    override fun getList(query: GatheringQuestionQuery): List<GatheringQuestion> {
        if (!gatheringPort.existsByUserIdAndGatheringId(
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

        return gatheringQuestionPort.findList(
            visitId = query.visitId
        )
    }
}
