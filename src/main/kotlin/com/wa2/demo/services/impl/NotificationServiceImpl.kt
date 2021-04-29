package com.wa2.demo.services.impl

import com.wa2.demo.domain.EmailVerificationToken
import com.wa2.demo.repositories.EmailVerificationTokenRepository
import com.wa2.demo.services.NotificationService
import com.wa2.demo.utils.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDateTime
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

        emailVerificationToken.username= username
        var token : UUID = UUID.randomUUID()
        emailVerificationToken.token = token.toString()
        emailVerificationToken.expirationTimestamp = Date()
        //Current time + 30 minutes



        println("Saving token ${token} ")

        emailVerificationTokenRepository.save(emailVerificationToken)

        return token

    }



    override fun verifyToken(token: UUID) : String? {

        println("Before query")
        println("Find row with token" + token)
        emailVerificationToken = emailVerificationTokenRepository.findEmailVerificationTokenByToken(token.toString())

        if(emailVerificationToken == null){

            println("Query result is null")
            return null

        }

        emailVerificationTokenRepository.removeEmailVerificationTokenByToken(token.toString())

        var dateFormatter : SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

        var today : Date = Date()
        var expiration : Date = dateFormatter.parse(emailVerificationToken?.expirationTimestamp.toString())

        //In minutes
        differenceBetweenDates = ((abs(expiration.time - today.time)) / (1000 * 60))


        println("Difference: " +  differenceBetweenDates)
//        println( "Difference: " +  (Date().time - dateFormatter.parse(emailVerificationToken.expirationTimestamp.toString()) )  )


        if(differenceBetweenDates!! < Constants.ExpiryTimeInMinutes){

            return emailVerificationToken!!.username

        }
        else
            return null




        return null

    }



}