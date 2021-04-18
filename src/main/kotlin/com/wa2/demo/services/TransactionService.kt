package com.wa2.demo.services

import com.wa2.demo.dto.TransactionDTO
import com.wa2.demo.domain.Transaction


interface TransactionService {
    fun createTransaction(transactionDTO: TransactionDTO): Int?
    fun transactionsByDate(walletId: Long, startDate: Long, endDate: Long): List<TransactionDTO>?
    fun getTransactionDetails(walletId: Long, transactionId: Long): Transaction?
}