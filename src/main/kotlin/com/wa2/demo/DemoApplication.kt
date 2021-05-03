package com.wa2.demo

import com.wa2.demo.domain.EmailVerificationToken
import com.wa2.demo.repositories.EmailVerificationTokenRepository
import com.wa2.demo.utils.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs


//@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
@SpringBootApplication
@EnableScheduling
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
class DemoApplication{

    @Autowired
    lateinit var emailVerificationTokenRepository: EmailVerificationTokenRepository

    @Value("\${spring.mail.host}")
    lateinit var host: String

    @Value("\${spring.mail.port}")
    lateinit var port: String

    @Value("\${spring.mail.username}")
    lateinit var username: String

    @Value("\${spring.mail.password}")
    lateinit var password: String

    @Value("\${spring.mail.properties.mail.smtp.auth}")
    lateinit var authProperty: String

    @Value("\${spring.mail.properties.mail.smtp.starttls.enable}")
    lateinit var startttlsMailProperty: String

    @Value("\${spring.mail.properties.mail.debug}")
    lateinit var debugProperty: String

    @Bean
    fun passwordEncoder() : PasswordEncoder {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder()
    }

    @Bean
    fun getMailSender() : JavaMailSender {

        val mailSender: JavaMailSenderImpl = JavaMailSenderImpl()


        println(host)
        println(port)
        println(username)
        println(password)

        mailSender.host = host
        mailSender.port = port.toInt()
        mailSender.username = username
        mailSender.password = password



        val javaMailProperties: Properties = Properties()

        javaMailProperties.put("mail.smtp.auth", authProperty == "true")
        javaMailProperties.put("mail.smtp.starttls.enable", startttlsMailProperty == "true")
        javaMailProperties.put("mail.debug", debugProperty == "true")


        mailSender.javaMailProperties = javaMailProperties


        return mailSender



    }

    @Bean
    fun getMailMessage(): SimpleMailMessage {

        return SimpleMailMessage()

    }

    @Scheduled(fixedRate =  60 * 60 * 1000)
    fun removeExpiredTokens() {
        //Remove expired timestamps
        var dateFormatter : SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")


        var emailVerificationTokenList : List<EmailVerificationToken> = emailVerificationTokenRepository.findAll()


        emailVerificationTokenList.forEach {
            var expiration: Date = dateFormatter.parse(it.expirationTimestamp.toString())
            var today : Date = Date()
            var differenceBetweenDates: Long = ((abs(expiration.time - today.time)) / (1000 * 60))
            if( differenceBetweenDates!! < Constants.ExpiryTimeInMinutes )
                emailVerificationTokenRepository.removeEmailVerificationTokenByToken(it.token.toString())

        }


    }


}


fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)
}
