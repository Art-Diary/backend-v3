package com.klieme.artdiary.application.service.command

import com.klieme.artdiary.application.port.`in`.command.*
import com.klieme.artdiary.application.port.out.SoloDiaryPort
import com.klieme.artdiary.application.port.out.VisitPort
import com.klieme.artdiary.common.exception.ArtdiaryException
import com.klieme.artdiary.common.exception.ErrorType
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
@Transactional
class SoloVisitCommandService(
    private val visitPort: VisitPort,
    private val soloDiaryPort: SoloDiaryPort
) : SoloVisitCommandUseCase {
    override fun createVisit(command: SoloVisitCreateCommand) {
        if (!visitPort.existsAvailableVisitDate(
                exhId = command.exhId,
                visitDate = command.visitDate
            )
        ) {
            throw ArtdiaryException(ErrorType.NOT_FOUND)
        }
        visitPort.saveSoloExh(
            exhId = command.exhId,
            userId = command.userId,
            visitDate = command.visitDate
        )
    }

    override fun createDiary(command: SoloDiaryCreateCommand) {
        if (!visitPort.existsByVisitIdAndUserId(
                visitId = command.visitId,
                userId = command.userId
            )
        ) {
            throw ArtdiaryException(ErrorType.NOT_FOUND)
        }

        command.diaryList.forEach { diary ->
            soloDiaryPort.save(
                visitId = command.visitId,
                questionId = diary.questionId,
                answerContent = diary.answerContent,
                isPublic = diary.isPublic
            )
        }
    }

    override fun updateDiary(command: SoloDiaryUpdateCommand) {
        soloDiaryPort.update(
            visitId = command.visitId,
            userId = command.userId,
            soloDiaryId = command.soloDiaryId,
            questionId = command.questionId,
            answerContent = command.answerContent,
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
