package com.wa2.demo.services.imp

import com.wa2.demo.dto.TransactionDTO
import com.wa2.demo.domain.Transaction
import com.wa2.demo.domain.Wallet
import com.wa2.demo.repositories.TransactionRepository
import com.wa2.demo.repositories.WalletRepository
import com.wa2.demo.services.TransactionService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

@Service
@Transactional
class TransactionServiceImpl(var transactionRepository: TransactionRepository, val walletRepository: WalletRepository) :
    TransactionService {
    override fun createTransaction(transactionDTO: TransactionDTO): Int? {
        try {
            val walletPayer: Wallet = walletRepository.findByWalletId(transactionDTO.payerWallet?.walletId!!)
            val walletPayee: Wallet = walletRepository.findByWalletId(transactionDTO.payeeWallet?.walletId!!)
            if (walletPayee != walletPayer) {
                val isBalanceGreaterThanZero: BigDecimal? = walletPayer.currentAmount?.subtract(transactionDTO.amount)
                if (isBalanceGreaterThanZero?.compareTo(BigDecimal.ZERO)!! >= 0) {
                    walletPayer.currentAmount = walletPayer.currentAmount?.subtract(transactionDTO.amount)
                    walletPayee.currentAmount = walletPayee.currentAmount?.add(transactionDTO.amount)
                    walletRepository.save(walletPayee)
                    walletRepository.save(walletPayer)
                    val transaction: Transaction = transactionRepository.save(transactionDTO.toTransactionEntity())
                    return transaction.transactionId?.toInt()
                }
            }
        } catch (e: Exception) {
            println(e.message.toString())
        }
        return 0
    }

    override fun transactionsByDate(walletId: Long, startDate: Long, endDate: Long): List<Transaction>? {
        val wallet = walletRepository.findById(walletId)

        val calStart = Calendar.getInstance()
        val calEnd = Calendar.getInstance()
        calStart.timeInMillis = startDate
        calEnd.timeInMillis = endDate

        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        val sDate = LocalDate.parse(sdf.format(calStart.time), DateTimeFormatter.ISO_DATE)
        val eDate = LocalDate.parse(sdf.format(calEnd.time), DateTimeFormatter.ISO_DATE)

        val queryStartDate = Date.from(sDate.atStartOfDay(ZoneId.systemDefault()).toInstant())
        val queryEndDate = Date.from(eDate.atStartOfDay(ZoneId.systemDefault()).toInstant())

        if (wallet.isPresent) {
            return transactionRepository.findTransactionByDateTimeBetween(queryStartDate, queryEndDate)
        }
        return null
    }
}

