package com.klieme.artdiary.application.dto

import com.klieme.artdiary.domain.Gathering

data class GatheringDetailDto(
    val gathering: Gathering,
    val gatheringMemberList: List<GatheringMemberResult>,
    val visitedExhList: List<GatheringVisitedExhResult>
)
