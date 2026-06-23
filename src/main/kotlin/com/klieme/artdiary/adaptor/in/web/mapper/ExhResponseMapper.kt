package com.klieme.artdiary.adaptor.`in`.web.mapper

import com.klieme.artdiary.adaptor.`in`.web.response.ExhListResponse
import com.klieme.artdiary.adaptor.`in`.web.response.ExhDetailResponse
import com.klieme.artdiary.application.dto.ExhDetailResult
import com.klieme.artdiary.application.dto.ExhListResult

fun ExhListResult.toListResponse(): ExhListResponse = ExhListResponse(
    exhId = exhId,
    exhName = exhName,
    gallery = gallery,
    startDate = startDate,
    endDate = endDate,
    painter = painter,
    poster = poster,
    liked = liked
)

fun ExhDetailResult.toDetailResponse(): ExhDetailResponse = ExhDetailResponse(
    exhId = exhId,
    exhName = exhName,
    gallery = gallery,
    startDate = startDate,
    endDate = endDate,
    painter = painter,
    fee = fee,
    intro = intro,
    homepageLink = homepageLink,
    poster = poster,
    source = source,
    liked = liked
)