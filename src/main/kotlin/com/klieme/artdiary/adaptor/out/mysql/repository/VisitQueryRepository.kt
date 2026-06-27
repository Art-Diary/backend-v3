package com.klieme.artdiary.adaptor.out.mysql.repository

import com.klieme.artdiary.application.dto.SoloVisitedExhResult

interface VisitQueryRepository {
    fun findVisitedExhList(
        userId: Long,
    ): List<SoloVisitedExhResult>
}