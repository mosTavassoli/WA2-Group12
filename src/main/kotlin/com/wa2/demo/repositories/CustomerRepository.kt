package com.wa2.demo.repositories

import com.wa2.demo.domain.Customer
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


@Repository
interface CustomerRepository : CrudRepository<Customer, Long>{
    fun findByCustomerId(customerId : Long): Customer
    fun findByEmail(email : String) : Customer
}