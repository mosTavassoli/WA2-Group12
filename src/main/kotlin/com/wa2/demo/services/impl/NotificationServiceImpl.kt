package com.wa2.demo.services.impl

import com.wa2.demo.domain.EmailVerificationToken
import com.wa2.demo.repositories.EmailVerificationTokenRepository
import com.wa2.demo.services.NotificationService
import com.wa2.demo.utils.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class NotificationServiceImpl : NotificationService {

    @Autowired
    lateinit var emailVerificationTokenRepository: EmailVerificationTokenRepository

    var emailVerificationToken: EmailVerificationToken? = null

    override fun saveToken(username: String) : UUID {

        var emailVerificationToken : EmailVerificationToken = EmailVerificationToken()

        emailVerificationToken.username= username
        var token : UUID = UUID.randomUUID()
        emailVerificationToken.token = token
        //Current time + 30 minutes
        emailVerificationToken.expirationTimestamp = System.currentTimeMillis() + Constants.ExpiryTimeInMilliseconds



        println("Saving token ${token} ")

        emailVerificationTokenRepository.save(emailVerificationToken)

        return token

    }



    override fun verifyToken(token: UUID) : String? {

        println("Before query")
        println("Find row with token" + token)
        emailVerificationToken = emailVerificationTokenRepository.findEmailVerificationTokenByToken(token)


        println( emailVerificationToken )



        if(emailVerificationToken == null){

            println("Query result is null")
            return null

        }

        println( "Time passed" + ((System.currentTimeMillis() - emailVerificationToken?.expirationTimestamp!!) / 60000) )


        //TODO Throw exceptions here
        if(emailVerificationToken!!.expirationTimestamp == null)
            return null
        else
        if( System.currentTimeMillis() - emailVerificationToken?.expirationTimestamp!! < Constants.ExpiryTimeInMilliseconds  )
            return emailVerificationToken!!.username
        else
            return null


    }



}