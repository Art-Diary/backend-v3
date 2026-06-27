package com.klieme.artdiary.application.port.out

import com.klieme.artdiary.application.dto.SoloVisitedExhResult
import java.time.LocalDate

interface VisitPort {
    fun findVisitedExhList(userId: Long): List<SoloVisitedExhResult>

    fun save(
        exhId: Long,
        userId: Long,
        visitDate: LocalDate
    )
}