package com.klieme.artdiary.domain

import java.util.*

data class Exh(
    val exhId: Long?,
    val exhName: String,
    val gallery: String,
    val startDate: Date,
    val endDate: Date,
    val painter: String?,
    val fee: Int,
    val intro: String?,
    val homepageLink: String?,
    val poster: String,
    val artField: String?,
    val source: String
)
