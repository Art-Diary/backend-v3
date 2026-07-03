package com.klieme.artdiary.adaptor.out.mysql.repository

import com.klieme.artdiary.adaptor.out.mysql.entity.GatheringEntity
import com.klieme.artdiary.adaptor.out.mysql.entity.GatheringMemberEntity
import com.klieme.artdiary.adaptor.out.mysql.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GatheringMemberJpaRepository : JpaRepository<GatheringMemberEntity, Long> {
    fun existsByUserAndGathering(
        user: UserEntity,
        gathering: GatheringEntity
    ): Boolean

    fun findByUserAndGathering(
        user: UserEntity,
        gathering: GatheringEntity
    ): GatheringMemberEntity?
}
