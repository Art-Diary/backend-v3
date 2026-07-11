package com.klieme.artdiary.application.port.`in`.query

import com.klieme.artdiary.application.dto.CalendarVisitResult

interface CalendarQueryUseCase {
    fun getData(query: CalendarQuery): List<CalendarVisitResult>
}
