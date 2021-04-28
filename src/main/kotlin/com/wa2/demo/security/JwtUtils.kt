package com.wa2.demo.security


import com.wa2.demo.dto.UserDetailsDTO
import com.wa2.demo.services.impl.UserDetailServiceImpl
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*


@Configuration
class JwtUtils {

    @Value("\${application.jwt.jwtSecret}")
    lateinit var jwtSecret: String

    @Value("\${application.jwt.jwtExpirationMs}")
    lateinit var expirationTime: String

    // fun for creating Key for JWT token Generator
    private fun getSigningKey(): Key? {
        val keyBytes = Decoders.BASE64.decode(this.jwtSecret)
        return Keys.hmacShaKeyFor(keyBytes)
    }


    fun generateJwtToken(authentication: Authentication): String {
        val userPrincipal: UserDetailsDTO = authentication.principal as UserDetailsDTO
        return Jwts.builder()
            .setIssuer(userPrincipal._username)
            .setExpiration(Date(System.currentTimeMillis() + expirationTime.toLong()))
            .signWith(getSigningKey())
            .compact()
    }

    fun validateJwtToken(authToken: String): Boolean {
        // TODO
        return true
    }


}

