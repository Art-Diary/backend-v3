package com.klieme.artdiary.adaptor.`in`.web.controller.command

import com.klieme.artdiary.adaptor.`in`.web.request.UserRequest
import com.klieme.artdiary.adaptor.`in`.web.response.ApiResponse
import com.klieme.artdiary.adaptor.`in`.web.response.ApiResult
import com.klieme.artdiary.adaptor.`in`.web.response.TokenResponse
import com.klieme.artdiary.application.port.`in`.command.UserCommand
import com.klieme.artdiary.application.port.`in`.command.UserCommandUseCase
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "User", description = "User 사용자 API")
@RestController
@RequestMapping("/api/users")
class UserCommandController(
    private val userCommandUseCase: UserCommandUseCase
) {
    @PostMapping
    fun login(@RequestBody request: UserRequest): ApiResult<TokenResponse> {
        val userCommand = UserCommand(
            providerType = request.providerType,
            providerUserId = request.providerUserId,
            alarmToken = request.alarmToken,
        )

        val userResponse = userCommandUseCase.login(userCommand)

        return ApiResponse.created(userResponse)
    }
}
