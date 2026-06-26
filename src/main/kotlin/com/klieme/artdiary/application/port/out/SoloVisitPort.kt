package com.klieme.artdiary.application.port.out

import com.klieme.artdiary.application.dto.SoloDiaryListResult
import com.klieme.artdiary.application.dto.SoloVisitedExhListResult

interface SoloVisitPort {
    fun findVisitedExhList(userId: Long): List<SoloVisitedExhListResult>

    fun findDiaryList(exhId: Long, userId: Long): List<SoloDiaryListResult>
}