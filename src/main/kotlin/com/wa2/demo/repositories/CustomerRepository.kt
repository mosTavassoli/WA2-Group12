package com.wa2.demo.repositories

import com.wa2.demo.entities.Customer
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


@Repository
interface CustomerRepository : CrudRepository<Customer , Long>{
}