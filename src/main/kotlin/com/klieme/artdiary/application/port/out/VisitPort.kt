package com.klieme.artdiary.application.port.out

import com.klieme.artdiary.application.dto.VisitedExhResult
import java.time.LocalDate

interface VisitPort {
    fun findSoloVisitedExhList(userId: Long): List<VisitedExhResult>

    fun save(
        exhId: Long,
        userId: Long,
        visitDate: LocalDate
    )

    fun findGatheringVisitedExhList(gatheringId: Long): List<VisitedExhResult>
}
