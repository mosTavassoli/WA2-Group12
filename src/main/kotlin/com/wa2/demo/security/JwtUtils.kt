package com.wa2.demo.security

//import io.jsonwebtoken.Jwts
//import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
//import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtUtils {

//    @Value("\${application.jwt.jwtSecret}")
//    lateinit var secretKey : String
//
//    @Value("\${application.jwt.jwtExpirationMs}")
//    lateinit var expirationTime : String
//
//    fun generateJwtToken (authentication: Authentication): String{
//        // we need a user ID
////        val issuer = UserDetailsDTO.id.toString()
//        val jwt = Jwts.builder()
//            .setIssuer(issuer)
//            .setExpiration(Date(System.currentTimeMillis() + expirationTime.toLong()))
//            .signWith()
//    }
}