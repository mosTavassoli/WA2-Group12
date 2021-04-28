package com.wa2.demo.services

import java.util.*

interface NotificationService {

    fun saveToken(username: String): UUID

}