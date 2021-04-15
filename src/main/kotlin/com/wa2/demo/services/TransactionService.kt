package com.wa2.demo.services

import com.wa2.demo.dto.TransactionDTO
import com.wa2.demo.entities.Transaction


interface TransactionService {
    fun createTransaction (transactionDTO: TransactionDTO):Int?
    fun transactionsByDate(walletId: Long, startDate:Long, endDate:Long): List<Transaction>?
}