package com.wa2.demo.security

import io.jsonwebtoken.Jwts
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class JwtAuthenticationTokenFilter : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val utils: JwtUtils = JwtUtils()

        val authHeader: String? = request.getHeader("authorization")

        val splittedJwt: List<String>? = authHeader?.split(' ')
        val jwt: String? = splittedJwt?.get(1)
        if (jwt?.let { utils.validateJwtToken(it) } == true) {
            val x = utils.getDetailsFromJwtToken(jwt)
            println(x)
        }

    }
}