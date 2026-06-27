package com.klieme.artdiary.adaptor.`in`.web.mapper

import com.klieme.artdiary.adaptor.`in`.web.response.ExhResponse
import com.klieme.artdiary.adaptor.`in`.web.response.ExhDetailResponse
import com.klieme.artdiary.adaptor.`in`.web.response.ExhReviewResponse
import com.klieme.artdiary.application.dto.ExhDetailResult
import com.klieme.artdiary.application.dto.ExhListResult
import com.klieme.artdiary.application.dto.ExhReviewResult

fun ExhListResult.toResponse(): ExhResponse = ExhResponse(
    exhId = exhId,
    exhName = exhName,
    gallery = gallery,
    startDate = startDate,
    endDate = endDate,
    painter = painter,
    poster = poster,
    liked = liked
)

fun ExhDetailResult.toResponse(): ExhDetailResponse = ExhDetailResponse(
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

fun ExhReviewResult.toResponse(): ExhReviewResponse = ExhReviewResponse(
    soloDiaryId = soloDiaryId,
    questionContent = questionContent,
    answerContent = answerContent,
    writeDate = writeDate,
    userId = userId,
    nickname = nickname,
    profile = profile
)