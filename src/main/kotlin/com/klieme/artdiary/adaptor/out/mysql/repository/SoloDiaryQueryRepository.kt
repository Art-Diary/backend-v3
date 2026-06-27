package com.klieme.artdiary.adaptor.out.mysql.repository

import com.klieme.artdiary.adaptor.out.mysql.entity.SoloDiaryEntity
import com.klieme.artdiary.application.dto.SoloDiaryResult

interface SoloDiaryQueryRepository {
    fun findDiaryList(
        visitId: Long,
        userId: Long,
    ): List<SoloDiaryResult>

    fun findDiary(
        visitId: Long,
        userId: Long,
        soloDiaryId: Long
    ): SoloDiaryEntity?
}