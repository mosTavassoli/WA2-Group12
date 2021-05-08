package com.wa2.demo.security

import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.web.filter.OncePerRequestFilter
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
        try {
            val authHeader: String? = request.getHeader("Authorization")
            if (authHeader?.startsWith("Bearer ") == true) {
                val headerArr: List<String> = authHeader.split(' ')
                val jwt: String = headerArr[1]
                if (jwtUtils.validateJwtToken(jwt)) {
                    val userDetailsDTO = jwtUtils.getDetailsFromJwtToken(jwt)

                    if (userDetailsDTO?.isEnabled == true) {
                        val authentication = UsernamePasswordAuthenticationToken(
                            userDetailsDTO,
                            null,
                            userDetailsDTO.authorities
                        )
                        authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
                        SecurityContextHolder.getContext().authentication = authentication
                    } else {
                        throw Exception("The User is not enabled!")
                    }
                } else {
                    SecurityContextHolder.clearContext()
                }
            } else {
                SecurityContextHolder.clearContext()
            }
        }catch (ex: Exception){
            request.setAttribute("Cannot set user authentication", ex.message)
        }

        filterChain.doFilter(request, response)
    }
}