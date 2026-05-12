package com.klieme.artdiary.application.service.command

import com.klieme.artdiary.adaptor.`in`.web.response.TokenResponse
import com.klieme.artdiary.application.port.`in`.command.UserCommand
import com.klieme.artdiary.application.port.`in`.command.UserCommandUseCase
import com.klieme.artdiary.application.port.out.UserPort
import com.klieme.artdiary.domain.User
import com.klieme.artdiary.infrastructure.jwt.JwtProvider
import jakarta.transaction.Transactional
import mu.KotlinLogging
import org.springframework.stereotype.Service
import java.time.LocalDateTime

private val logger = KotlinLogging.logger {}

@Service
@Transactional
class UserCommandService(
    private val userPort: UserPort,
    private val jwtProvider: JwtProvider
): UserCommandUseCase {
    override fun login(command: UserCommand): TokenResponse {
        // 회원가입 여부 확인
        val user = userPort.findByProvider(
            command.providerType,
            command.providerUserId
        )?: run {
            val newUser = User(
                providerType = command.providerType,
                providerUserId = command.providerUserId,
            )

            userPort.save(newUser)
        }

        val userId = requireNotNull(user.userId)

        val accessToken = jwtProvider.createAccessToken(userId)
        val refreshToken = jwtProvider.createRefreshToken(userId)

        logger.info { "accessToken: $accessToken" }

        userPort.updateRefreshToken(
            userId = userId,
            refreshToken = refreshToken,
            refreshTokenExpiredAt = LocalDateTime.now().plusDays(14)
        )

        return TokenResponse(
            accessToken = accessToken,
            refreshToken = refreshToken
        )
    }
}
