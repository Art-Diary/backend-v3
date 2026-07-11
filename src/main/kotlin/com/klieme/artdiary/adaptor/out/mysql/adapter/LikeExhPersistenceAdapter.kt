package com.klieme.artdiary.adaptor.out.mysql.adapter

import com.klieme.artdiary.adaptor.out.mysql.entity.LikeExhEntity
import com.klieme.artdiary.adaptor.out.mysql.repository.LikeExhJpaRepository
import com.klieme.artdiary.application.port.out.LikeExhPort
import com.klieme.artdiary.common.exception.ArtdiaryException
import com.klieme.artdiary.common.exception.ErrorType
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class LikeExhPersistenceAdapter(
    private val jpaRepository: LikeExhJpaRepository
) : LikeExhPort {
    override fun exists(exhId: Long, userId: Long): Boolean =
        jpaRepository.existsByExhIdAndUserId(
            exhId,
            userId
        )

    override fun save(exhId: Long, userId: Long) {
        if (jpaRepository.existsByExhIdAndUserId(
                exhId = exhId,
                userId = userId
        )) {
            throw ArtdiaryException(ErrorType.CONFLICT, "Exhibition Already Liked")
        }
        try {
            jpaRepository.save(
                LikeExhEntity(
                    exhId = exhId,
                    userId = userId,
                    createdAt = LocalDateTime.now()
                )
            )
        } catch (e: Exception) {
            throw ArtdiaryException(ErrorType.CONFLICT, "Exhibition Already Liked")
        }
    }

    override fun delete(exhId: Long, userId: Long): Long =
        jpaRepository.deleteByExhIdAndUserId(
            exhId,
            userId
        )
}
