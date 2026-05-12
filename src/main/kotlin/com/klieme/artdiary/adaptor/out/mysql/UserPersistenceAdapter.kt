package com.klieme.artdiary.adaptor.out.mysql

import com.klieme.artdiary.application.port.out.UserPort
import com.klieme.artdiary.common.exception.ArtdiaryException
import com.klieme.artdiary.common.exception.ErrorType
import com.klieme.artdiary.domain.ProviderType
import com.klieme.artdiary.domain.User
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class UserPersistenceAdapter(
    private val jpaRepository: UserJpaRepository
): UserPort {
    override fun findByProvider(providerType: ProviderType, providerUserId: String): User? {
        return jpaRepository.findByProviderTypeAndProviderUserId(
            providerType,
            providerUserId
        )?.let(UserMapper::toDomain)
    }

    override fun save(user: User): User {
        val newUser = jpaRepository.save(UserMapper.toEntity(user))

        return UserMapper.toDomain(newUser)
    }

    override fun updateRefreshToken(userId: Long, refreshToken: String, refreshTokenExpiredAt: LocalDateTime) {
        val userEntity = jpaRepository.findByUserId(userId)
            ?: throw ArtdiaryException(ErrorType.NOT_FOUND)

        userEntity.updateRefreshToken(
            refreshToken = refreshToken,
            refreshTokenExpiredAt = refreshTokenExpiredAt
        )
    }
}
