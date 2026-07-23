package com.klieme.artdiary.adaptor.out.mysql.repository

import com.klieme.artdiary.application.dto.GatheringListResult
import com.klieme.artdiary.application.dto.GatheringMemberResult

interface GatheringQueryRepository {
    fun findList(
        userId: Long,
    ): List<GatheringListResult>

    fun findMemberList(
        gatheringId: Long,
        userId: Long,
    ): List<GatheringMemberResult>
}
