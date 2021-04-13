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
    override fun createTransaction(transactionDTO: TransactionDTO) :Int? {
        try {
            val wallet: Wallet = walletRepository.findByWalletId(transactionDTO.payeeWallet?.walletId!!)
            wallet.currentAmount  =
                BigDecimal.valueOf(wallet.currentAmount!!.toLong()) +
                        BigDecimal.valueOf(transactionDTO.amount?.toLong()!!)
            walletRepository.save(wallet)
            val transaction: Transaction = transactionRepository.save(transactionDTO.toTransactionEntity())
            return transaction.transactionId?.toInt()
        } catch (e: Exception) {
            println(e.message.toString())
        }
        return 0
    }
}

