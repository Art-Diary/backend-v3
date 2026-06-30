package com.klieme.artdiary.adaptor.out.mysql.repository

import com.klieme.artdiary.adaptor.out.mysql.entity.GatheringEntity
import com.klieme.artdiary.application.dto.GatheringMemberResult

interface GatheringQueryRepository {
    fun findList(
        userId: Long,
    ): List<GatheringEntity>

    fun findMemberList(
        gatheringId: Long,
        userId: Long,
    ): List<GatheringMemberResult>
}
