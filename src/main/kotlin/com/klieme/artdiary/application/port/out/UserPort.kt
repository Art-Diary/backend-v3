package com.klieme.artdiary.application.port.out

import com.klieme.artdiary.domain.ProviderType
import com.klieme.artdiary.domain.User
import java.time.LocalDateTime

interface UserPort {
    fun findByProvider(
        providerType: ProviderType,
        providerUserId: String
    ): User?

    fun save(user: User): User

    fun updateRefreshToken(
        userId: Long,
        refreshToken: String,
        refreshTokenExpiredAt: LocalDateTime
    )
}
