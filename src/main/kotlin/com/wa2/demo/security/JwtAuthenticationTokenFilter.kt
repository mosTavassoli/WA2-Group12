package com.wa2.demo.security

import io.jsonwebtoken.Jwts
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.security.SignatureException
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Configuration
class JwtAuthenticationTokenFilter(val jwtUtils: JwtUtils) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authHeader: String? = request.getHeader("Authorization")

        val headerArr: List<String>? = authHeader?.split(' ')
        val jwt: String? = headerArr?.get(1)
        if (jwt?.let { jwtUtils.validateJwtToken(it) } == true) {
            val userDetailsDTO = jwtUtils.getDetailsFromJwtToken(jwt)

            val authentication = UsernamePasswordAuthenticationToken(userDetailsDTO,
                null,
                userDetailsDTO?.authorities)
            authentication.details = WebAuthenticationDetailsSource() .buildDetails(request)
            SecurityContextHolder.getContext().authentication = authentication
        }
        filterChain.doFilter(request, response)

    }
}