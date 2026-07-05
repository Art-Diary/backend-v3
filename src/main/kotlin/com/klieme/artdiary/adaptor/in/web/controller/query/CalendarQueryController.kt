package com.klieme.artdiary.adaptor.`in`.web.controller.query

import com.klieme.artdiary.adaptor.`in`.web.mapper.toResponse
import com.klieme.artdiary.adaptor.`in`.web.response.ApiResponse
import com.klieme.artdiary.adaptor.`in`.web.response.ApiResult
import com.klieme.artdiary.adaptor.`in`.web.response.CalendarResponse
import com.klieme.artdiary.application.port.`in`.query.CalendarQuery
import com.klieme.artdiary.application.port.`in`.query.CalendarQueryUseCase
import com.klieme.artdiary.infrastructure.jwt.CustomUserDetails
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Calendar", description = "Calendar 캘린더 API")
@RestController
@RequestMapping("/api/calendar")
class CalendarQueryController(
    private val calendarQueryUseCase: CalendarQueryUseCase
) {
    @GetMapping
    fun getData(
        @RequestParam(value = "year") year: Int,
        @RequestParam(value = "month") month: Int,
        @AuthenticationPrincipal user: CustomUserDetails
    ): ApiResult<List<CalendarResponse>> {
        val query = CalendarQuery(
            userId = user.userId,
            year = year,
            month = month
        )

        val result = calendarQueryUseCase.getData(query)
        val response = result.map { it.toResponse() }

        return ApiResponse.get(response)
    }
}
