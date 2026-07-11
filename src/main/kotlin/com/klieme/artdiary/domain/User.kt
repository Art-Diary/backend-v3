package com.klieme.artdiary.domain

import java.time.LocalDateTime

data class User(
    val userId: Long? = null,
    val nickname: String? = null,
    val profile: String? = null,
    val artField: String? = null,
    val roleType: RoleType = RoleType.USER,
    val providerType: ProviderType,
    val providerUserId: String,
    val refreshToken: String? = null,
    val refreshTokenExpiredAt: LocalDateTime? = null,
    val alarmToken: String? = null,
    val deletedAt: LocalDateTime? = null
)
