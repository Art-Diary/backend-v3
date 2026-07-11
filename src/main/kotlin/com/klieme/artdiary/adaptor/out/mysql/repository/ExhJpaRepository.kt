package com.klieme.artdiary.adaptor.out.mysql.repository

import com.klieme.artdiary.adaptor.out.mysql.entity.ExhEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface ExhJpaRepository : JpaRepository<ExhEntity, Long> {
    fun existsByExhId(exhId: Long): Boolean

    @Modifying
    @Query(
        """
        update ExhEntity e
        set e.likeCount = e.likeCount + 1
        where e.exhId = :exhId
    """
    )
    fun increaseLikeCount(exhId: Long): Int

    @Modifying
    @Query(
        """
        update ExhEntity e
        set e.likeCount = e.likeCount - 1
        where e.exhId = :exhId and e.likeCount > 0
    """
    )
    fun decreaseLikeCount(exhId: Long): Int

    fun existsByExhIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
        exhId: Long,
        visitDate: LocalDate,
        visitDate2: LocalDate
    ): Boolean
}
