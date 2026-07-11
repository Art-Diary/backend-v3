package com.klieme.artdiary.adaptor.out.mysql.repository

import com.klieme.artdiary.application.dto.CalendarVisitResult
import com.klieme.artdiary.application.dto.GatheringVisitedExhResult
import com.klieme.artdiary.application.dto.SoloVisitedExhResult

interface VisitQueryRepository {
    fun findSoloVisitedExhList(
        userId: Long,
    ): List<SoloVisitedExhResult>

    fun findGatheringVisitedExhList(
        gatheringId: Long,
    ): List<GatheringVisitedExhResult>

    fun findCalendarData(
        userId: Long,
        year: Int,
        month: Int
    ): List<CalendarVisitResult>
}
