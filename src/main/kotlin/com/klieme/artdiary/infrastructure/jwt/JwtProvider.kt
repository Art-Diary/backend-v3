package com.klieme.artdiary.infrastructure.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Header
import io.jsonwebtoken.Jwt
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.SecretKey

@Component
class JwtProvider(
    @Value("\${jwt.secret}")
    secretKey: String,

    @Value("\${jwt.access-token-expired-time}")
    private val accessTokenExpireTime: Long,

    @Value("\${jwt.refresh-token-expired-time}")
    private val refreshTokenExpireTime: Long
) {

    private val key: SecretKey =
        Keys.hmacShaKeyFor(secretKey.toByteArray())

    fun createAccessToken(userId: Long): String {
        return createToken(
            userId = userId,
            expireTime = accessTokenExpireTime
        )
    }

    fun createRefreshToken(userId: Long): String {
        return createToken(
            userId = userId,
            expireTime = refreshTokenExpireTime
        )
    }

    private fun createToken(
        userId: Long,
        expireTime: Long
    ): String {
        val now = Date()
        val expiration = Date(now.time + expireTime)

        return Jwts.builder()
            .subject(userId.toString())
            .issuedAt(now)
            .expiration(expiration)
            .signWith(key)
            .compact()
    }

    fun getUserId(token: String): Long {
        return parseClaims(token)
            .payload
            .subject
            .toLong()
    }

    fun validateToken(token: String): Boolean {
        return try {
            parseClaims(token)
            true
        } catch (e: Exception) {
            false
        }
    }

    private fun parseClaims(token: String): Jwt<out Header, Claims> {
        return Jwts.parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(token)
    }
}
