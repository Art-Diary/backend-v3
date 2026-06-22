package com.klieme.artdiary.adaptor.out.mysql

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface ExhJpaRepository : JpaRepository<ExhEntity, Long> {
    fun findByExhNameContainingIgnoreCaseOrGalleryContainingIgnoreCaseOrPainterContainingIgnoreCaseOrderByExhNameAsc(
        exhName: String,
        gallery: String,
        painter: String
    ): List<ExhEntity>

    fun findByStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByExhNameAsc(date1: LocalDate, date2: LocalDate): List<ExhEntity>

    fun findByExhId(exhId: Long): ExhEntity?
}
