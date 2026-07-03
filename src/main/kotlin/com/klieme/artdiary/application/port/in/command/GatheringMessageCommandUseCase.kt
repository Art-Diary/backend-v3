package com.klieme.artdiary.application.port.`in`.command

interface GatheringMessageCommandUseCase {
    fun create(command: GatheringMessageCreateCommand)

    fun update(command: GatheringMessageUpdateCommand)

    fun delete(command: GatheringMessageDeleteCommand)
}
