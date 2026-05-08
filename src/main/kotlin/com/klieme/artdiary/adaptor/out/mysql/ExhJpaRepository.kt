package com.klieme.artdiary.adaptor.out.mysql

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface ExhJpaRepository: JpaRepository<ExhEntity, Long> {
    fun findByExhId(exhId: Long): Optional<ExhEntity>
}
