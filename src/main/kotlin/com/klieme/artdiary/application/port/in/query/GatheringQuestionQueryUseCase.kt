package com.klieme.artdiary.application.port.`in`.query

import com.klieme.artdiary.domain.GatheringQuestion

interface GatheringQuestionQueryUseCase {
    fun getList(query: GatheringQuestionQuery): List<GatheringQuestion>
}
