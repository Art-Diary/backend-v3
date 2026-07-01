package com.klieme.artdiary.adaptor.out.mysql.adapter

import com.klieme.artdiary.adaptor.out.mysql.entity.GatheringEntity
import com.klieme.artdiary.adaptor.out.mysql.entity.GatheringMemberEntity
import com.klieme.artdiary.adaptor.out.mysql.mapper.GatheringMapper
import com.klieme.artdiary.adaptor.out.mysql.repository.GatheringJpaRepository
import com.klieme.artdiary.adaptor.out.mysql.repository.GatheringMemberJpaRepository
import com.klieme.artdiary.adaptor.out.mysql.repository.GatheringQueryRepository
import com.klieme.artdiary.adaptor.out.mysql.repository.UserJpaRepository
import com.klieme.artdiary.application.dto.GatheringMemberResult
import com.klieme.artdiary.application.port.out.GatheringPort
import com.klieme.artdiary.common.exception.ArtdiaryException
import com.klieme.artdiary.common.exception.ErrorType
import com.klieme.artdiary.domain.Gathering
import org.springframework.stereotype.Component

@Component
class GatheringPersistenceAdapter(
    private val queryRepository: GatheringQueryRepository,
    private val jpaRepository: GatheringJpaRepository,
    private val gatheringMemberJpaRepository: GatheringMemberJpaRepository,
    private val userJpaRepository: UserJpaRepository
) : GatheringPort {
    override fun findList(userId: Long): List<Gathering> {
        return queryRepository.findList(userId).map { GatheringMapper.toDomain(it) }
    }

    override fun findById(gatheringId: Long): Gathering {
        val entity = jpaRepository.findById(gatheringId)
            .orElseThrow {
                ArtdiaryException(ErrorType.NOT_FOUND)
            }

        return GatheringMapper.toDomain(entity)
    }

    override fun findMemberList(gatheringId: Long, userId: Long): List<GatheringMemberResult> {
        val list = queryRepository.findMemberList(
            gatheringId = gatheringId,
            userId = userId
        )

        return list.ifEmpty {
            throw ArtdiaryException(ErrorType.NOT_FOUND)
        }
    }

    override fun existsByCode(code: String): Boolean {
        return jpaRepository.existsByCode(code)
    }

    override fun saveGathering(
        name: String,
        code: String
    ): Long {
        val entity = jpaRepository.save(
            GatheringEntity(
                name = name,
                code = code
            )
        )

        return requireNotNull(entity.id)
    }

    override fun saveGatheringMember(
        userId: Long,
        gatheringId: Long
    ) {
        gatheringMemberJpaRepository.save(
            GatheringMemberEntity(
                user = userJpaRepository.getReferenceById(userId),
                gathering = jpaRepository.getReferenceById(gatheringId)
            )
        )
    }

    override fun findByCode(code: String): Long {
        val entity = jpaRepository.findByCode(code)
            ?: throw ArtdiaryException(ErrorType.NOT_FOUND)

        return requireNotNull(entity.id)
    }

    override fun existsByUserIdAndGatheringId(userId: Long, gatheringId: Long): Boolean {
        return gatheringMemberJpaRepository.existsByUserAndGathering(
            user = userJpaRepository.getReferenceById(userId),
            gathering = jpaRepository.getReferenceById(gatheringId)
        )
    }
}
