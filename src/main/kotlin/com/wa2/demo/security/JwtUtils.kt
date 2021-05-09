package com.wa2.demo.security


import com.wa2.demo.dto.UserDetailsDTO
import com.wa2.demo.dto.toUserDetailsDTO
import com.wa2.demo.repositories.UserRepository
import io.jsonwebtoken.*
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.SignatureException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*
import javax.servlet.http.HttpServletRequest


@Component
class JwtUtils {

    @Autowired
    lateinit var httpServletRequest: HttpServletRequest

    @Value("\${application.jwt.jwtSecret}")
    lateinit var jwtSecret: String

    @Value("\${application.jwt.jwtExpirationMs}")
    lateinit var expirationTime: String


    @Autowired
    lateinit var userRepository: UserRepository


//    private fun getSigningKey(): Key? {
//        val keyBytes = Decoders.BASE64.decode(jwtSecret)
//        return Keys.hmacShaKeyFor(keyBytes)
//    }

    fun generateJwtToken(authentication: Authentication): String {
        val userPrincipal: UserDetailsDTO = authentication.principal as UserDetailsDTO
        return Jwts.builder()
            .setIssuer(userPrincipal.userId.toString())
            .claim("roles", userPrincipal.roles)
            .setHeaderParam("typ", "JWT")
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + expirationTime.toLong()))
            .signWith( SignatureAlgorithm.HS256,jwtSecret)
            .compact()
    }

    fun validateJwtToken(authToken: String): Boolean {
        try {
            Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(authToken)
            return true
        } catch (ex: SignatureException) {
            httpServletRequest.setAttribute("Invalid JWT signature", ex.message)
        } catch (ex: MalformedJwtException) {
            httpServletRequest.setAttribute("Invalid JWT token", ex.message)
        } catch (ex: ExpiredJwtException) {
            httpServletRequest.setAttribute("JWT token is expired", ex.message)
        } catch (ex: UnsupportedJwtException) {
            httpServletRequest.setAttribute("JWT token is unsupported", ex.message)
        } catch (ex: IllegalArgumentException) {
            httpServletRequest.setAttribute("JWT claims string is empty", ex.message)
        }
        return false
    }

    fun getDetailsFromJwtToken(authToken: String): UserDetailsDTO? {
        val getDetailsFromJwtToken =
            Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(authToken)
        val userDetailsDTO = userRepository.findByUsername(getDetailsFromJwtToken.body["sub"].toString())?.toUserDetailsDTO()
//        userDetailsDTO._username = getDetailsFromJwtToken.body["iss"].toString()
//        if (userDetailsDTO?.roles?.contains("ADMIN") == true) AuthorityUtils.createAuthorityList("ROLE_ADMIN")
        return userDetailsDTO
    }
}

