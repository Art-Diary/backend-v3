package com.klieme.artdiary.application.service.command

import com.klieme.artdiary.application.port.`in`.command.GatheringCommandUseCase
import com.klieme.artdiary.application.port.`in`.command.GatheringCreateCommand
import com.klieme.artdiary.application.port.`in`.command.GatheringJoinCommand
import com.klieme.artdiary.application.port.`in`.command.GatheringVisitExhCommand
import com.klieme.artdiary.application.port.out.GatheringMemberPort
import com.klieme.artdiary.application.port.out.GatheringPort
import com.klieme.artdiary.application.port.out.VisitPort
import com.klieme.artdiary.common.exception.ArtdiaryException
import com.klieme.artdiary.common.exception.ErrorType
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.security.SecureRandom

@Service
@Transactional
class GatheringCommandService(
    private val gatheringPort: GatheringPort,
    private val gatheringMemberPort: GatheringMemberPort,
    private val visitPort: VisitPort
) : GatheringCommandUseCase {
    private val CHARS = "ABCDEFGHJKLMNPQRSTUVWXYZ23456789"

    private val random = SecureRandom()

    private fun generateCode(length: Int = 6): String =
        buildString(length) {
            repeat(length) {
                append(CHARS[random.nextInt(CHARS.length)])
            }
        }

    override fun create(command: GatheringCreateCommand) {
        var code: String

        do {
            code = generateCode()
        } while (gatheringPort.existsByCode(code))

        val gatheringId = gatheringPort.saveGathering(
            name = command.name,
            code = code
        )

        gatheringMemberPort.save(
            userId = command.userId,
            gatheringId = gatheringId
        )
    }

    override fun join(command: GatheringJoinCommand) {
        val gatheringId = gatheringPort.findByCode(command.code)

        if (!gatheringMemberPort.existsByUserIdAndGatheringId(
                gatheringId = gatheringId,
                userId = command.userId
            )
        ) {
            gatheringMemberPort.save(
                userId = command.userId,
                gatheringId = gatheringId
            )
        }
    }

    override fun visitExh(command: GatheringVisitExhCommand) {
        if (!gatheringMemberPort.existsByUserIdAndGatheringId(
                gatheringId = command.gatheringId,
                userId = command.userId
            )
        ) {
            throw ArtdiaryException(ErrorType.NOT_FOUND)
        }

        if (!visitPort.existsAvailableVisitDate(
                exhId = command.exhId,
                visitDate = command.visitDate
            )
        ) {
            throw ArtdiaryException(ErrorType.NOT_FOUND)
        }

        if (visitPort.existsByExhIdAndGatheringIdAndVisitDate(
                exhId = command.exhId,
                gatheringId = command.gatheringId,
                visitDate = command.visitDate
            )
        ) {
            throw ArtdiaryException(ErrorType.CONFLICT)
        }

        visitPort.saveGatheringExh(
            exhId = command.exhId,
            gatheringId = command.gatheringId,
            visitDate = command.visitDate
        )
    }
}
