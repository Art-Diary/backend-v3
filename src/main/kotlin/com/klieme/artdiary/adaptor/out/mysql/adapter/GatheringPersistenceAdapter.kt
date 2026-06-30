package com.klieme.artdiary.adaptor.out.mysql.adapter

import com.klieme.artdiary.adaptor.out.mysql.mapper.GatheringMapper
import com.klieme.artdiary.adaptor.out.mysql.repository.GatheringJpaRepository
import com.klieme.artdiary.adaptor.out.mysql.repository.GatheringQueryRepository
import com.klieme.artdiary.application.dto.GatheringMemberResult
import com.klieme.artdiary.application.port.out.GatheringPort
import com.klieme.artdiary.common.exception.ArtdiaryException
import com.klieme.artdiary.common.exception.ErrorType
import com.klieme.artdiary.domain.Gathering
import org.springframework.stereotype.Component

@Component
class GatheringPersistenceAdapter(
    private val queryRepository: GatheringQueryRepository,
    private val jpaRepository: GatheringJpaRepository
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
}
