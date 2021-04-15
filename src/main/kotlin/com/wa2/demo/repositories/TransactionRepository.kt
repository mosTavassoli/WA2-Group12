package com.wa2.demo.repositories

import com.wa2.demo.dto.TransactionDTO
import com.wa2.demo.entities.Transaction
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TransactionRepository : CrudRepository<Transaction , Long>{
    fun findTransactionByDateTimeBetween(startDate: Date, endDate: Date): List<Transaction>?
}