package com.wa2.demo.security


import com.wa2.demo.dto.UserDetailsDTO
import io.jsonwebtoken.*
import io.jsonwebtoken.security.SignatureException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
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

        val userDetailsDTO = UserDetailsDTO()
        userDetailsDTO._username = getDetailsFromJwtToken.body["iss"].toString()
        return userDetailsDTO
    }
}

