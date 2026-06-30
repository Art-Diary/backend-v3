package com.klieme.artdiary.application.port.out

import com.klieme.artdiary.application.dto.GatheringMemberResult
import com.klieme.artdiary.domain.Gathering

interface GatheringPort {
    fun findList(userId: Long): List<Gathering>

    fun findById(gatheringId: Long): Gathering

    fun findMemberList(
        gatheringId: Long,
        userId: Long
    ): List<GatheringMemberResult>
}
