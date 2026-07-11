package com.klieme.artdiary.adaptor.out.mysql.mapper

import com.klieme.artdiary.adaptor.out.mysql.entity.GatheringEntity
import com.klieme.artdiary.domain.Gathering

object GatheringMapper {
    fun toDomain(entity: GatheringEntity): Gathering =
        Gathering(
            id = requireNotNull(entity.id),
            name = entity.name,
            code = entity.code
        )
}
