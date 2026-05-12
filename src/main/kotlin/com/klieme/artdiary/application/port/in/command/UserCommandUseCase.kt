package com.klieme.artdiary.application.port.`in`.command

import com.klieme.artdiary.adaptor.`in`.web.response.TokenResponse

interface UserCommandUseCase {
    fun login(command: UserCommand): TokenResponse
}
