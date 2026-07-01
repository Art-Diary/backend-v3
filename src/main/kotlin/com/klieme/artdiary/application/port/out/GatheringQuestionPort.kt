package com.klieme.artdiary.application.port.out

import com.klieme.artdiary.domain.GatheringQuestion

interface GatheringQuestionPort {
    fun findList(
        visitId: Long
    ): List<GatheringQuestion>
}
