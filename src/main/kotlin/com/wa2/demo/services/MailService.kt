package com.wa2.demo.services

import java.util.*


interface MailService {

    fun sendMessage(email: String, token: UUID)

}