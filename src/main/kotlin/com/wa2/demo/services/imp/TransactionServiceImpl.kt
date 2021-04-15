package com.wa2.demo.services.imp

import com.wa2.demo.dto.TransactionDTO
import com.wa2.demo.entities.Transaction
import com.wa2.demo.entities.Wallet
import com.wa2.demo.repositories.TransactionRepository
import com.wa2.demo.repositories.WalletRepository
import com.wa2.demo.services.TransactionService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

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
}

