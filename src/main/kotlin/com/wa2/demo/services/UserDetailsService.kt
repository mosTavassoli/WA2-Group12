package com.wa2.demo.services

import com.wa2.demo.dto.UserDetailsDTO
import com.wa2.demo.utils.RoleNames
import org.springframework.security.core.userdetails.UserDetailsService
import java.util.*


interface UserDetailsService : UserDetailsService {
    fun addUser(
        username: String,
        password: String,
        email: String,
        isEnabled: Boolean?,
        roles: List<RoleNames>?
    ): UserDetailsDTO?

    fun addUserRole(username: String, role: RoleNames)
    fun removeUserRole(username: String, role: RoleNames)
    fun enableUser(username: String)
    fun disableUser(username: String)

    //    fun loadUserByUsername(username:String) : UserDetailsDTO
    fun verifyToken(token: UUID)
}