package com.klieme.artdiary.application.service.command

import com.klieme.artdiary.application.port.`in`.command.LikeExhCommand
import com.klieme.artdiary.application.port.`in`.command.LikeExhCommandUseCase
import com.klieme.artdiary.application.port.out.ExhPort
import com.klieme.artdiary.application.port.out.LikeExhPort
import com.klieme.artdiary.common.exception.ArtdiaryException
import com.klieme.artdiary.common.exception.ErrorType
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
@Transactional
class LikeExhCommandService(
    private val exhPort: ExhPort,
    private val likeExhPort: LikeExhPort
) : LikeExhCommandUseCase {
    override fun likeExecute(command: LikeExhCommand) {
        if (!exhPort.existsByExhId(command.exhId)) {
            throw ArtdiaryException(ErrorType.NOT_FOUND)
        }

        likeExhPort.save(command.exhId, command.userId)

        exhPort.increaseLikeCount(command.exhId)
    }

    override fun unlikeExecute(command: LikeExhCommand) {
        if (!exhPort.existsByExhId(command.exhId)) {
            throw ArtdiaryException(ErrorType.NOT_FOUND)
        }

        val deleteCount = likeExhPort.delete(command.exhId, command.userId)

        if (deleteCount > 0) {
            exhPort.decreaseLikeCount(command.exhId)
        }
    }
}