package com.klieme.artdiary.adaptor.out.mysql.repository

import com.klieme.artdiary.application.dto.SoloDiaryListResult
import com.klieme.artdiary.application.dto.SoloVisitedExhListResult

interface SoloVisitQueryRepository {
    fun findVisitedExhList(
        userId: Long,
    ): List<SoloVisitedExhListResult>

    fun findDiaryList(
        exhId: Long,
        userId: Long,
    ): List<SoloDiaryListResult>
}