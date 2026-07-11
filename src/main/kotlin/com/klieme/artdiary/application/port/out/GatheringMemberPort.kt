package com.klieme.artdiary.application.port.out

interface GatheringMemberPort {
    fun save(
        userId: Long,
        gatheringId: Long
    )

    fun existsByUserIdAndGatheringId(
        userId: Long,
        gatheringId: Long
    ): Boolean

    fun findByUserIdAndGatheringId(
        userId: Long,
        gatheringId: Long
    ): Long
}
