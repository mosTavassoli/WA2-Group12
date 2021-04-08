package com.wa2.demo.repositories

import com.wa2.demo.entities.Transaction
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TransactionRepository : CrudRepository<Transaction , Long>{

}