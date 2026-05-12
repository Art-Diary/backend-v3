package com.klieme.artdiary.adaptor.out.mysql

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ExhJpaRepository: JpaRepository<ExhEntity, Long> {
    fun findByExhId(exhId: Long): ExhEntity?
}
