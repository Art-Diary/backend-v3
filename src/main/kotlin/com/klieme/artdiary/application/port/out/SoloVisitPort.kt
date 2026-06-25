package com.klieme.artdiary.application.port.out

import com.klieme.artdiary.application.dto.SoloVisitDiaryListResult
import com.klieme.artdiary.application.dto.SoloVisitTicketListResult

interface SoloVisitPort {
    fun findTicketList(userId: Long): List<SoloVisitTicketListResult>

    fun findDiaryList(exhId: Long, userId: Long): List<SoloVisitDiaryListResult>
}