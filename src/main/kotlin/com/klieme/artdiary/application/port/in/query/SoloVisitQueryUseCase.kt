package com.klieme.artdiary.application.port.`in`.query

import com.klieme.artdiary.application.dto.SoloDiaryResult
import com.klieme.artdiary.application.dto.SoloVisitedExhResult

interface SoloVisitQueryUseCase {
    fun getVisitedExhList(userId: Long): List<SoloVisitedExhResult>

    fun getDiaryList(query: SoloDiaryListQuery): List<SoloDiaryResult>
}
