package com.wa2.demo.services.imp

import com.wa2.demo.dto.TransactionDTO
import com.wa2.demo.entities.Transaction
import com.wa2.demo.repositories.TransactionRepository
import com.wa2.demo.repositories.WalletRepository
import com.wa2.demo.services.TransactionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.math.log

@Service
@Transactional
class TransactionServiceImpl(var transactionRepository: TransactionRepository) : TransactionService {
    override fun createTransaction(transactionDTO: TransactionDTO ){
        val transaction : Transaction = transactionRepository.save(transactionDTO.toTransactionEntity())
        println("TransactionID: ${transaction.transactionId}")

    }
}

