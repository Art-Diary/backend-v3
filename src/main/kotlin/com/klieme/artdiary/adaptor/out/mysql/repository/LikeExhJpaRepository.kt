package com.klieme.artdiary.adaptor.out.mysql.repository

import com.klieme.artdiary.adaptor.out.mysql.entity.LikeExhEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LikeExhJpaRepository : JpaRepository<LikeExhEntity, Long> {
    fun existsByExhIdAndUserId(exhId: Long, userId: Long): Boolean

    fun deleteByExhIdAndUserId(exhId: Long, userId: Long): Long
}