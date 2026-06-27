package com.klieme.artdiary.application.port.out

import com.klieme.artdiary.application.dto.SoloVisitedExhResult

interface VisitPort {
    fun findVisitedExhList(userId: Long): List<SoloVisitedExhResult>
}