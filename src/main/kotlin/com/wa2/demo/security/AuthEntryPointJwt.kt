package com.wa2.demo.security


import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthEntryPointJwt : AuthenticationEntryPoint {
    override fun commence(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authException: AuthenticationException?
    ) {
        val invalidSIN = request?.getAttribute("Invalid JWT signature")
        val invalidTKN = request?.getAttribute("Invalid JWT token")
        val expiredTKN = request?.getAttribute("JWT token is expired")
        val unsupportedTKN = request?.getAttribute("JWT token is unsupported")
        val emptySTR = request?.getAttribute("JWT claims string is empty")

        when {
            invalidSIN != null -> {
                response?.setHeader("UNAUTHORIZED", "FormBased")
                response?.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT signature")
            }
            invalidTKN != null -> {
                response?.setHeader("UNAUTHORIZED", "FormBased")
                response?.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT token")
            }
            expiredTKN != null -> {
                response?.setHeader("UNAUTHORIZED", "FormBased")
                response?.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT token is expired")
            }
            unsupportedTKN != null -> {
                response?.setHeader("UNAUTHORIZED", "FormBased")
                response?.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT token is unsupported")
            }
            emptySTR != null -> {
                response?.setHeader("UNAUTHORIZED", "FormBased")
                response?.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT claims string is empty")
            }
            else -> {
                response?.setHeader("UNAUTHORIZED", "FormBased")
                response?.sendError(401, authException?.message)
            }
        }

    }
}