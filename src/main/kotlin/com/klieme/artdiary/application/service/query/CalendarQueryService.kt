package com.klieme.artdiary.application.service.query

import com.klieme.artdiary.application.dto.CalendarVisitResult
import com.klieme.artdiary.application.port.`in`.query.CalendarQuery
import com.klieme.artdiary.application.port.`in`.query.CalendarQueryUseCase
import com.klieme.artdiary.application.port.out.VisitPort
import org.springframework.stereotype.Service

@Service
class CalendarQueryService(
    private val visitPort: VisitPort
) : CalendarQueryUseCase {
    override fun getData(query: CalendarQuery): List<CalendarVisitResult> {
        return visitPort.findCalendarData(
            userId = query.userId,
            year = query.year,
            month = query.month
        )
    }
}
