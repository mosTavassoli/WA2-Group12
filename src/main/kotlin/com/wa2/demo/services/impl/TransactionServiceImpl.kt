package com.wa2.demo.services.impl

import com.wa2.demo.dto.TransactionDTO
import com.wa2.demo.domain.Transaction
import com.wa2.demo.dto.toTransactionDTO
import com.wa2.demo.repositories.TransactionRepository
import com.wa2.demo.repositories.WalletRepository
import com.wa2.demo.services.TransactionService
import org.springframework.beans.factory.annotation.Autowired
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
class TransactionServiceImpl(
    @Autowired var transactionRepository: TransactionRepository,
    @Autowired val walletRepository: WalletRepository,
) :
    TransactionService {
    override fun createTransaction(transactionDTO: TransactionDTO): Int? {
        try {
//            val walletPayer: Wallet = transactionDTO.payerWallet?.walletId.let { walletRepository.findByWalletId(it!!) }
            val walletPayerOp = walletRepository.findById(transactionDTO.payerWallet?.walletId!!)
            val walletPayeeOp = walletRepository.findById(transactionDTO.payeeWallet?.walletId!!)
//            val walletPayee: Wallet = transactionDTO.payeeWallet?.walletId.let { walletRepository.findByWalletId(it!!) }
            if (walletPayeeOp.isPresent && walletPayerOp.isPresent) {
                val walletPayer = walletPayerOp.get()
                val walletPayee = walletPayeeOp.get()
                if (walletPayee != walletPayer) {
                    val isBalanceGreaterThanZero: BigDecimal? =
                        walletPayer.currentAmount?.subtract(transactionDTO.amount)
                    if (isBalanceGreaterThanZero?.compareTo(BigDecimal.ZERO)!! >= 0) {
                        walletPayer.currentAmount = walletPayer.currentAmount?.subtract(transactionDTO.amount)
                        walletPayee.currentAmount = walletPayee.currentAmount?.add(transactionDTO.amount)
                        walletRepository.save(walletPayee)
                        walletRepository.save(walletPayer)
                        val transaction: Transaction = transactionRepository.save(transactionDTO.toTransactionEntity())
                        return transaction.transactionId?.toInt()
                    }
                }
            } else throw Exception("Payee or Payer not found")
        } catch (e: Exception) {
            throw e
        }
        return 0
    }

    override fun transactionsByDate(walletId: Long, startDate: Long, endDate: Long): List<TransactionDTO>? {
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
            var result = transactionRepository.findTransactionByDateTimeBetween(queryStartDate, queryEndDate)?.map {a -> a.toTransactionDTO()}
            return result
        } else
        throw Exception("The wallet not found")
    }

    override fun getTransactionDetails(walletId: Long, transactionId: Long): Transaction? {
        try {
            val walletOp = walletRepository.findById(walletId)
            if (!walletOp.isPresent) throw Exception("Wallet not found")
            return transactionRepository.findTransactionByTransactionIdAndPayerWallet(transactionId, walletOp.get())
                ?: throw Exception("Transaction not found")
        } catch (ex: Exception) {
            throw ex
        }
    }
}

