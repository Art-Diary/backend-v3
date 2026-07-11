package com.klieme.artdiary.adaptor.`in`.web.mapper

import com.klieme.artdiary.adaptor.`in`.web.response.GatheringDetailResponse
import com.klieme.artdiary.adaptor.`in`.web.response.GatheringMemberResponse
import com.klieme.artdiary.adaptor.`in`.web.response.GatheringResponse
import com.klieme.artdiary.application.dto.GatheringDetailDto
import com.klieme.artdiary.application.dto.GatheringMemberResult
import com.klieme.artdiary.domain.Gathering

fun Gathering.toResponse(): GatheringResponse = GatheringResponse(
    id = id,
    name = name
)

fun GatheringDetailDto.toResponse(): GatheringDetailResponse = GatheringDetailResponse(
    id = gathering.id,
    name = gathering.name,
    code = gathering.code,
    memberList = gatheringMemberList.map { it.toResponse() },
    visitedExhList = visitedExhList.map { it.toResponse() }
)

fun GatheringMemberResult.toResponse(): GatheringMemberResponse = GatheringMemberResponse(
    id = userId,
    nickname = nickname,
    profile = profile
)

