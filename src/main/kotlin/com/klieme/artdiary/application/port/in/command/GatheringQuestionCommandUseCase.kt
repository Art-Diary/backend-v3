package com.klieme.artdiary.application.port.`in`.command

interface GatheringQuestionCommandUseCase {
    fun create(command: GatheringQuestionCreateCommand)

    fun update(command: GatheringQuestionUpdateCommand)
}
