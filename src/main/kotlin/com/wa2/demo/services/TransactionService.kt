package com.wa2.demo.services

import com.wa2.demo.dto.TransactionDTO


interface TransactionService {
    fun createTransaction (transactionDTO: TransactionDTO):Int?
}