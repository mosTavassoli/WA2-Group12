package com.wa2.demo.repositories

import com.wa2.demo.domain.EmailVerificationToken
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface EmailVerificationTokenRepository : CrudRepository<EmailVerificationToken,UUID> {

    fun findEmailVerificationTokenByToken(token: UUID): EmailVerificationToken


}