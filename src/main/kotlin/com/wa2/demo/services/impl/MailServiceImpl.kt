package com.wa2.demo.services.impl

import com.wa2.demo.services.MailService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service
import java.util.*

@Service
class MailServiceImpl(  ) : MailService {

    @Autowired lateinit var mailSender : JavaMailSender

    @Autowired lateinit var message: SimpleMailMessage


    override fun sendMessage(email: String, token: UUID) {



        message.setSubject("Confirm your Email")
        message.setText("Your token: " + token)
        message.setTo(email)

        try{

            mailSender.send(message)
            println("Mail sent")

        } catch ( ex: Exception ){
            throw ex
        }

    }


}