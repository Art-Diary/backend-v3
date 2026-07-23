package com.klieme.artdiary.application.port.out

import com.klieme.artdiary.application.dto.GatheringListResult
import com.klieme.artdiary.application.dto.GatheringMemberResult
import com.klieme.artdiary.domain.Gathering

interface GatheringPort {
    fun findList(userId: Long): List<GatheringListResult>

    fun findById(gatheringId: Long): Gathering

    fun findMemberList(
        gatheringId: Long,
        userId: Long
    ): List<GatheringMemberResult>

    fun existsByCode(code: String): Boolean

    fun saveGathering(
        name: String,
        code: String
    ): Long

    fun findByCode(code: String): Long
}
