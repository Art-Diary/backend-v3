package com.klieme.artdiary.application.port.`in`.command

interface SoloVisitCommandUseCase {
    fun createVisit(command: SoloVisitCreateCommand)

    fun createDiary(command: SoloDiaryCreateCommand)

    fun updateDiary(command: SoloDiaryUpdateCommand)

    fun deleteDiary(command: SoloDiaryDeleteCommand)
}
