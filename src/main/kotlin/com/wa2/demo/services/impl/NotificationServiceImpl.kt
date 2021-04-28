package com.wa2.demo.services.impl

import com.wa2.demo.domain.EmailVerificationToken
import com.wa2.demo.repositories.EmailVerificationTokenRepository
import com.wa2.demo.services.NotificationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class NotificationServiceImpl : NotificationService {

    @Autowired
    lateinit var emailVerificationTokenRepository: EmailVerificationTokenRepository

    override fun saveToken(username: String) : UUID {

        var emailVerificationToken : EmailVerificationToken = EmailVerificationToken()

        emailVerificationToken.username= username
        var token : UUID = UUID.randomUUID()
        emailVerificationToken.token = token

        emailVerificationTokenRepository.save(emailVerificationToken)

        return token

    }



}