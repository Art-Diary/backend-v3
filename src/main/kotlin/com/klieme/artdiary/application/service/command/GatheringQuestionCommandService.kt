package com.klieme.artdiary.application.service.command

import com.klieme.artdiary.application.port.`in`.command.GatheringQuestionCommandUseCase
import com.klieme.artdiary.application.port.`in`.command.GatheringQuestionCreateCommand
import com.klieme.artdiary.application.port.`in`.command.GatheringQuestionUpdateCommand
import com.klieme.artdiary.application.port.out.GatheringPort
import com.klieme.artdiary.application.port.out.GatheringQuestionPort
import com.klieme.artdiary.application.port.out.VisitPort
import com.klieme.artdiary.common.exception.ArtdiaryException
import com.klieme.artdiary.common.exception.ErrorType
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
@Transactional
class GatheringQuestionCommandService(
    private val gatheringPort: GatheringPort,
    private val visitPort: VisitPort,
    private val gatheringQuestionPort: GatheringQuestionPort
) : GatheringQuestionCommandUseCase {
    override fun create(command: GatheringQuestionCreateCommand) {
        if (!gatheringPort.existsByUserIdAndGatheringId(
                userId = command.userId,
                gatheringId = command.gatheringId
            )
        ) {
            throw ArtdiaryException(ErrorType.NOT_FOUND)
        }

        if (!visitPort.existsByIdAndGatheringId(
                gatheringId = command.gatheringId,
                id = command.visitId
            )
        ) {
            throw ArtdiaryException(ErrorType.NOT_FOUND)
        }

        gatheringQuestionPort.save(
            visitId = command.visitId,
            content = command.content
        )
    }

    override fun update(command: GatheringQuestionUpdateCommand) {
        if (!gatheringPort.existsByUserIdAndGatheringId(
                userId = command.userId,
                gatheringId = command.gatheringId
            )
        ) {
            throw ArtdiaryException(ErrorType.NOT_FOUND)
        }

        if (!visitPort.existsByIdAndGatheringId(
                gatheringId = command.gatheringId,
                id = command.visitId
            )
        ) {
            throw ArtdiaryException(ErrorType.NOT_FOUND)
        }

        gatheringQuestionPort.update(
            id = command.questionId,
            visitId = command.visitId,
            content = command.content
        )
    }
}
