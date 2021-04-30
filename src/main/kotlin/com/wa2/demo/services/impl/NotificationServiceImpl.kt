package com.wa2.demo.services.impl

import com.wa2.demo.domain.EmailVerificationToken
import com.wa2.demo.repositories.EmailVerificationTokenRepository
import com.wa2.demo.services.NotificationService
import com.wa2.demo.utils.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

@Service
class NotificationServiceImpl : NotificationService {

    @Autowired
    lateinit var emailVerificationTokenRepository: EmailVerificationTokenRepository

    var emailVerificationToken: EmailVerificationToken? = null

    var differenceBetweenDates: Long? = null

    override fun saveToken(username: String) : UUID {

        var emailVerificationToken : EmailVerificationToken = EmailVerificationToken()

        println("saving username: " + username)

        emailVerificationToken.username= username.replace( "\"" , "" )
        var token : UUID = UUID.randomUUID()
        emailVerificationToken.token = token.toString()
        emailVerificationToken.expirationTimestamp = Date()
        //Current time + 30 minutes




        emailVerificationTokenRepository.save(emailVerificationToken)

        return token

    }



    override fun verifyToken(token: UUID) : String? {

        emailVerificationToken = emailVerificationTokenRepository.findEmailVerificationTokenByToken(token.toString())

        if(emailVerificationToken == null){

            return null

        }

        emailVerificationTokenRepository.removeEmailVerificationTokenByToken(token.toString())

        var dateFormatter : SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

        var today : Date = Date()
        var expiration : Date = dateFormatter.parse(emailVerificationToken?.expirationTimestamp.toString())

        //In minutes
        differenceBetweenDates = ((abs(expiration.time - today.time)) / (1000 * 60))




        if(differenceBetweenDates!! < Constants.ExpiryTimeInMinutes){

            //Remove expired timestamps

            var emailVerificationTokenList : List<EmailVerificationToken> = emailVerificationTokenRepository.findAll()


            emailVerificationTokenList.forEach {
                expiration = dateFormatter.parse(it.expirationTimestamp.toString())
                differenceBetweenDates = ((abs(expiration.time - today.time)) / (1000 * 60))
                if( differenceBetweenDates!! < Constants.ExpiryTimeInMinutes )
                    emailVerificationTokenRepository.removeEmailVerificationTokenByToken(it.token.toString())

            }

            return emailVerificationToken!!.username



        }
        else
            return null


        //TODO Automatic task to remove expired instances

        return null

    }



}