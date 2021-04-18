package com.wa2.demo.repositories

import com.wa2.demo.domain.Transaction
import com.wa2.demo.domain.Wallet
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TransactionRepository : CrudRepository<Transaction, Long> {
    fun findTransactionByDateTimeBetween(startDate: Date, endDate: Date): List<Transaction>?
    fun findTransactionByTransactionIdAndPayerWallet(transactionId: Long, walletPayer: Wallet) : Transaction?
//    fun findByPayerWallet(walletPayer: Wallet, transactionId: Long): List<Transaction?>
}