package com.klieme.artdiary.application.service.command

import com.klieme.artdiary.application.port.`in`.command.GatheringMessageCommandUseCase
import com.klieme.artdiary.application.port.`in`.command.GatheringMessageCreateCommand
import com.klieme.artdiary.application.port.`in`.command.GatheringMessageDeleteCommand
import com.klieme.artdiary.application.port.`in`.command.GatheringMessageUpdateCommand
import com.klieme.artdiary.application.port.out.*
import com.klieme.artdiary.common.exception.ArtdiaryException
import com.klieme.artdiary.common.exception.ErrorType
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
@Transactional
class GatheringMessageCommandService(
    private val gatheringMemberPort: GatheringMemberPort,
    private val visitPort: VisitPort,
    private val gatheringQuestionPort: GatheringQuestionPort,
    private val gatheringMessagePort: GatheringMessagePort
) : GatheringMessageCommandUseCase {
    override fun create(command: GatheringMessageCreateCommand) {
        val memberId = gatheringMemberPort.findByUserIdAndGatheringId(
            userId = command.userId,
            gatheringId = command.gatheringId
        )

        if (!visitPort.existsByIdAndGatheringId(
                gatheringId = command.gatheringId,
                id = command.visitId
            )
        ) {
            throw ArtdiaryException(ErrorType.NOT_FOUND)
        }

        if (!gatheringQuestionPort.existsByIdAndVisitId(
                id = command.questionId,
                visitId = command.visitId
            )
        ) {
            throw ArtdiaryException(ErrorType.NOT_FOUND)
        }

        gatheringMessagePort.save(
            questionId = command.questionId,
            memberId = memberId,
            content = command.content
        )
    }

    override fun update(command: GatheringMessageUpdateCommand) {
        gatheringMessagePort.update(
            id = command.messageId,
            userId = command.userId,
            content = command.content
        )
    }

    override fun delete(command: GatheringMessageDeleteCommand) {
        gatheringMessagePort.delete(
            id = command.messageId,
            userId = command.userId
        )
    }
}
