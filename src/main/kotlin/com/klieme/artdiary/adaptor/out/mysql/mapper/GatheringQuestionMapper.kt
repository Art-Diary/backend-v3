package com.klieme.artdiary.adaptor.out.mysql.mapper

import com.klieme.artdiary.adaptor.out.mysql.entity.GatheringQuestionEntity
import com.klieme.artdiary.domain.GatheringQuestion

object GatheringQuestionMapper {
    fun toDomain(entity: GatheringQuestionEntity): GatheringQuestion =
        GatheringQuestion(
            id = requireNotNull(entity.id),
            content = entity.content,
        )
}
