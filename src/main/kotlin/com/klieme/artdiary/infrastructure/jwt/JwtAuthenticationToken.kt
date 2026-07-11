package com.klieme.artdiary.infrastructure.jwt

import org.springframework.security.authentication.AbstractAuthenticationToken

class JwtAuthenticationToken(
    private val userDetails: CustomUserDetails
) : AbstractAuthenticationToken(emptyList()) {

    override fun getCredentials(): Any? = null

    override fun getPrincipal(): Any = userDetails

    init {
        isAuthenticated = true
    }
}
