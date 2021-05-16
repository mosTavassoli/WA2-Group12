package com.wa2.demo.repositories

import com.wa2.demo.domain.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<User, Long>{
    fun findByUsername(username: String): User?
}