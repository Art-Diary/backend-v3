package com.klieme.artdiary.application.port.`in`.command

interface GatheringCommandUseCase {
    fun create(command: GatheringCreateCommand)

    fun join(command: GatheringJoinCommand)

    fun visitExh(command: GatheringVisitExhCommand)
}
