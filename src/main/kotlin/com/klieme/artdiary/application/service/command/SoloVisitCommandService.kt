package com.klieme.artdiary.application.service.command

import com.klieme.artdiary.application.port.`in`.command.*
import com.klieme.artdiary.application.port.out.SoloDiaryPort
import com.klieme.artdiary.application.port.out.VisitPort
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
@Transactional
class SoloVisitCommandService(
    private val visitPort: VisitPort,
    private val soloDiaryPort: SoloDiaryPort
) : SoloVisitCommandUseCase {
    override fun createVisit(command: SoloVisitCreateCommand) {
        visitPort.save(
            exhId = command.exhId,
            userId = command.userId,
            visitDate = command.visitDate
        )
    }

    override fun createDiary(command: SoloDiaryCreateCommand) {
        soloDiaryPort.save(
            visitId = command.visitId,
            userId = command.userId,
            questionId = command.questionId,
            answerContent = command.answerContent,
            writeDate = command.writeDate,
            isPublic = command.isPublic
        )
    }

    override fun updateDiary(command: SoloDiaryUpdateCommand) {
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

    override fun deleteDiary(command: SoloDiaryDeleteCommand) {
        soloDiaryPort.delete(
            visitId = command.visitId,
            userId = command.userId,
            soloDiaryId = command.soloDiaryId
        )
    }
}