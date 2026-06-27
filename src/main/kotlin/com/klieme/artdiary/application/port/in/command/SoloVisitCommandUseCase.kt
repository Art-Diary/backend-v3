package com.klieme.artdiary.application.port.`in`.command

interface SoloVisitCommandUseCase {
    fun create(command: SoloVisitCreateCommand)

    fun update(command: SoloVisitUpdateCommand)

    fun delete(command: SoloVisitDeleteCommand)
}