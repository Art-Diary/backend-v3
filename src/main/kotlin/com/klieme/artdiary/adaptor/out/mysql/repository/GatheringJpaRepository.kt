package com.klieme.artdiary.adaptor.out.mysql.repository

import com.klieme.artdiary.adaptor.out.mysql.entity.GatheringEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GatheringJpaRepository : JpaRepository<GatheringEntity, Long> {
    fun existsByCode(code: String): Boolean

    fun findByCode(code: String): GatheringEntity?
}
