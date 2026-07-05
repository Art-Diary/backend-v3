package com.klieme.artdiary.application.port.`in`.query

data class CalendarQuery(
    val userId: Long,
    val year: Int,
    val month: Int
)
