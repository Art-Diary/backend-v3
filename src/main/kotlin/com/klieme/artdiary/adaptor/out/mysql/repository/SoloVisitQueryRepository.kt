package com.klieme.artdiary.adaptor.out.mysql.repository

import com.klieme.artdiary.application.dto.SoloVisitDiaryListResult
import com.klieme.artdiary.application.dto.SoloVisitTicketListResult

interface SoloVisitQueryRepository {
    fun findTicketList(
        userId: Long,
    ): List<SoloVisitTicketListResult>

    fun findDiaryList(
        exhId: Long,
        userId: Long,
    ): List<SoloVisitDiaryListResult>
}