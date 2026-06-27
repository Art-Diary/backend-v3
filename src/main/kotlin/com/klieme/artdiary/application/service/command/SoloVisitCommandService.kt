package com.klieme.artdiary.application.service.command

import com.klieme.artdiary.application.port.`in`.command.SoloVisitCreateCommand
import com.klieme.artdiary.application.port.`in`.command.SoloVisitCommandUseCase
import com.klieme.artdiary.application.port.`in`.command.SoloVisitDeleteCommand
import com.klieme.artdiary.application.port.`in`.command.SoloVisitUpdateCommand
import com.klieme.artdiary.application.port.out.SoloDiaryPort
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
@Transactional
class SoloVisitCommandService(
    private val soloDiaryPort: SoloDiaryPort
) : SoloVisitCommandUseCase {
    override fun create(command: SoloVisitCreateCommand) {
        soloDiaryPort.save(
            visitId = command.visitId,
            userId = command.userId,
            questionId = command.questionId,
            answerContent = command.answerContent,
            writeDate = command.writeDate,
            isPublic = command.isPublic
        )
    }

    override fun update(command: SoloVisitUpdateCommand) {
        soloDiaryPort.update(
            visitId = command.visitId,
            userId = command.userId,
            soloDiaryId = command.soloDiaryId,
            questionId = command.questionId,
            answerContent = command.answerContent,
            writeDate = command.writeDate,
            isPublic = command.isPublic
        )
    }

    override fun delete(command: SoloVisitDeleteCommand) {
        soloDiaryPort.delete(
            visitId = command.visitId,
            userId = command.userId,
            soloDiaryId = command.soloDiaryId
        )
    }
}