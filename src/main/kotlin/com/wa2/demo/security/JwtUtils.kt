package com.wa2.demo.security


import com.wa2.demo.dto.UserDetailsDTO
import com.wa2.demo.services.impl.UserDetailServiceImpl
import io.jsonwebtoken.*
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.security.Key
import java.security.SignatureException
import java.util.*


@Configuration
class JwtUtils {

    @Value("\${application.jwt.jwtSecret}")
    lateinit var jwtSecret: String

    @Value("\${application.jwt.jwtExpirationMs}")
    lateinit var expirationTime: String

    // fun for creating Key for JWT token Generator
//    private fun getSigningKey(): Key? {
//        val keyBytes = Decoders.BASE64.decode(this.jwtSecret)
//        return Keys.hmacShaKeyFor(keyBytes)
//    }


    fun generateJwtToken(authentication: Authentication): String {
        val userPrincipal: UserDetailsDTO = authentication.principal as UserDetailsDTO
        return Jwts.builder()
            .setIssuer(userPrincipal.username)
            .setExpiration(Date(System.currentTimeMillis() + expirationTime.toLong()))
            .signWith(SignatureAlgorithm.HS256, jwtSecret)
            .compact()
    }

    fun validateJwtToken(authToken: String): Boolean {
        try {
            Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(authToken)
            return true
        } catch (ex: ExpiredJwtException) {
            throw ex
        } catch (ex: SignatureException) {
            throw ex
        } catch (ex: MalformedJwtException) {
            throw ex
        } catch (ex: UnsupportedJwtException) {
            throw ex
        }
        return false
    }

    fun getDetailsFromJwtToken(authToken: String): UserDetailsDTO? {
        val getDetailsFromJwtToken: String =
            Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(authToken).toString()

        val userDetailsDTO = UserDetailsDTO()

        return userDetailsDTO

    }


}

