package com.wa2.demo.services.imp

import com.wa2.demo.dto.TransactionDTO
import com.wa2.demo.repositories.TransactionRepository
import com.wa2.demo.repositories.WalletRepository
import com.wa2.demo.services.TransactionService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class TransactionServiceImpl(val transactionRepository: TransactionRepository) : TransactionService {
    override fun createTransaction(transactionDTO: TransactionDTO) {
        println(transactionDTO)
        // TODO save Transaction
//        transactionRepository.save(transactionDTO)
    }
}

