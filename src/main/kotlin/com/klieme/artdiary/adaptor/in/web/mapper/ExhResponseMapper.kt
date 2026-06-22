package com.klieme.artdiary.adaptor.`in`.web.mapper

import com.klieme.artdiary.adaptor.`in`.web.response.ExhListResponse
import com.klieme.artdiary.adaptor.`in`.web.response.ExhResponse
import com.klieme.artdiary.domain.Exh

fun Exh.toListResponse(): ExhListResponse = ExhListResponse(
    exhId = exhId,
    exhName = exhName,
    gallery = gallery,
    startDate = startDate,
    endDate = endDate,
    painter = painter,
    poster = poster,
)

fun Exh.toDetailResponse(): ExhResponse = ExhResponse(
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
    source = source
)