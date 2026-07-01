package com.klieme.artdiary.adaptor.`in`.web.mapper

import com.klieme.artdiary.adaptor.`in`.web.response.GatheringQuestionResponse
import com.klieme.artdiary.domain.GatheringQuestion

fun GatheringQuestion.toResponse(): GatheringQuestionResponse = GatheringQuestionResponse(
    id = id,
    content = content
)
