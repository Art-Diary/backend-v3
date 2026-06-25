package com.klieme.artdiary.application.port.`in`.query

import com.klieme.artdiary.application.dto.SoloVisitDiaryListResult
import com.klieme.artdiary.application.dto.SoloVisitTicketListResult

interface SoloVisitQueryUseCase {
    fun getTicketList(userId: Long): List<SoloVisitTicketListResult>

    fun getDiaryList(query: SoloVisitDiaryListQuery): List<SoloVisitDiaryListResult>
}