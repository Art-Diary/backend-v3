package com.klieme.artdiary.adaptor.`in`.web.request

import com.klieme.artdiary.domain.ProviderType

data class UserRequest (
    val providerType: ProviderType,
    val providerUserId: String,
    val alarmToken: String?
)
