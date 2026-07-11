package com.klieme.artdiary.infrastructure.jwt

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
    private val jwtProvider: JwtProvider
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        val bearerToken = request.getHeader("Authorization")
        val token = bearerToken
            ?.takeIf {
                it.startsWith("Bearer ")
            }
            ?.substring(7)

        if (token != null && jwtProvider.validateToken(token)) {
            val userId = jwtProvider.getUserId(token)
            val authentication = JwtAuthenticationToken(
                CustomUserDetails(userId)
            )

            SecurityContextHolder.getContext().authentication = authentication
        }
        filterChain.doFilter(request, response)
    }
}
