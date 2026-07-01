package com.klieme.artdiary.adaptor.out.mysql.repository

import com.klieme.artdiary.adaptor.out.mysql.entity.ExhEntity
import com.klieme.artdiary.adaptor.out.mysql.entity.GatheringEntity
import com.klieme.artdiary.adaptor.out.mysql.entity.UserEntity
import com.klieme.artdiary.adaptor.out.mysql.entity.VisitEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface VisitJpaRepository : JpaRepository<VisitEntity, Long> {
    fun findByIdAndUser(
        id: Long,
        user: UserEntity
    ): VisitEntity?

    fun existsByExhAndUserAndVisitDate(
        exh: ExhEntity,
        user: UserEntity,
        visitDate: LocalDate
    ): Boolean

    fun existsByExhAndGatheringAndVisitDate(
        exh: ExhEntity,
        gathering: GatheringEntity,
        visitDate: LocalDate
    ): Boolean
}
