package com.klieme.artdiary.adaptor.out.mysql.repository

import com.klieme.artdiary.adaptor.out.mysql.entity.GatheringQuestionEntity
import com.klieme.artdiary.adaptor.out.mysql.entity.VisitEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GatheringQuestionJpaRepository : JpaRepository<GatheringQuestionEntity, Long> {
    fun findByVisit(visit: VisitEntity): List<GatheringQuestionEntity>

    fun findByIdAndVisit(
        id: Long,
        visit: VisitEntity
    ): GatheringQuestionEntity?

    fun existsByIdAndVisit(
        id: Long,
        visit: VisitEntity
    ): Boolean
}
