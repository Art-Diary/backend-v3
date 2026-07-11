package com.klieme.artdiary.adaptor.out.mysql.adapter

import com.klieme.artdiary.adaptor.out.mysql.entity.GatheringMemberEntity
import com.klieme.artdiary.adaptor.out.mysql.repository.GatheringJpaRepository
import com.klieme.artdiary.adaptor.out.mysql.repository.GatheringMemberJpaRepository
import com.klieme.artdiary.adaptor.out.mysql.repository.UserJpaRepository
import com.klieme.artdiary.application.port.out.GatheringMemberPort
import com.klieme.artdiary.common.exception.ArtdiaryException
import com.klieme.artdiary.common.exception.ErrorType
import org.springframework.stereotype.Component

@Component
class GatheringMemberAdapter(
    private val jpaRepository: GatheringMemberJpaRepository,
    private val userJpaRepository: UserJpaRepository,
    private val gatheringJpaRepository: GatheringJpaRepository
) : GatheringMemberPort {
    override fun save(userId: Long, gatheringId: Long) {
        jpaRepository.save(
            GatheringMemberEntity(
                user = userJpaRepository.getReferenceById(userId),
                gathering = gatheringJpaRepository.getReferenceById(gatheringId)
            )
        )
    }

    override fun existsByUserIdAndGatheringId(userId: Long, gatheringId: Long): Boolean {
        return jpaRepository.existsByUserAndGathering(
            user = userJpaRepository.getReferenceById(userId),
            gathering = gatheringJpaRepository.getReferenceById(gatheringId)
        )
    }

    override fun findByUserIdAndGatheringId(userId: Long, gatheringId: Long): Long {
        val entity = jpaRepository.findByUserAndGathering(
            user = userJpaRepository.getReferenceById(userId),
            gathering = gatheringJpaRepository.getReferenceById(gatheringId)
        ) ?: throw ArtdiaryException(ErrorType.NOT_FOUND)

        return requireNotNull(entity.id)
    }
}
