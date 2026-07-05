package com.klieme.artdiary.adaptor.out.mysql.repository

import com.klieme.artdiary.application.dto.CalendarVisitResult
import com.klieme.artdiary.application.dto.VisitedExhResult

interface VisitQueryRepository {
    fun findSoloVisitedExhList(
        userId: Long,
    ): List<VisitedExhResult>

    fun findGatheringVisitedExhList(
        gatheringId: Long,
    ): List<VisitedExhResult>

    fun findCalendarData(
        userId: Long,
        year: Int,
        month: Int
    ): List<CalendarVisitResult>
}
