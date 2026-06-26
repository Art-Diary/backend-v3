package com.klieme.artdiary.application.port.`in`.query

import com.klieme.artdiary.application.dto.SoloDiaryListResult
import com.klieme.artdiary.application.dto.SoloVisitedExhListResult

interface SoloVisitQueryUseCase {
    fun getVisitedExhList(userId: Long): List<SoloVisitedExhListResult>

    fun getDiaryList(query: SoloDiaryListQuery): List<SoloDiaryListResult>
}