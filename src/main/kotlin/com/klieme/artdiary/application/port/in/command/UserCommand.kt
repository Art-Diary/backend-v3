package com.klieme.artdiary.application.port.`in`.command

import com.klieme.artdiary.domain.ProviderType

data class UserCommand(
    val providerType: ProviderType,
    val providerUserId: String,
    val alarmToken: String?
)
