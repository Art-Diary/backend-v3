package com.klieme.artdiary.application.port.out

import com.klieme.artdiary.application.dto.CalendarVisitResult
import com.klieme.artdiary.application.dto.VisitedExhResult
import java.time.LocalDate

interface VisitPort {
    fun findSoloVisitedExhList(userId: Long): List<VisitedExhResult>

    fun saveSoloExh(
        exhId: Long,
        userId: Long,
        visitDate: LocalDate
    )

    fun findGatheringVisitedExhList(gatheringId: Long): List<VisitedExhResult>

    fun existsAvailableVisitDate(
        exhId: Long,
        visitDate: LocalDate
    ): Boolean

    fun existsByExhIdAndGatheringIdAndVisitDate(
        gatheringId: Long,
        exhId: Long,
        visitDate: LocalDate
    ): Boolean

    fun saveGatheringExh(
        exhId: Long,
        gatheringId: Long,
        visitDate: LocalDate
    )

    fun existsByIdAndGatheringId(
        gatheringId: Long,
        id: Long
    ): Boolean

    fun findCalendarData(
        userId: Long,
        year: Int,
        month: Int
    ): List<CalendarVisitResult>
}
