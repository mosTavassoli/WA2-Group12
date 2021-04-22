package com.wa2.demo.services

import com.wa2.demo.domain.User
import com.wa2.demo.dto.CustomerDTO
import com.wa2.demo.dto.UserDetailsDTO
import com.wa2.demo.dto.WalletDTO
import com.wa2.demo.utils.RoleNames


interface UserDetailsService {
    fun addUser (username: String, password: String, email: String, isEnabled: Boolean?, roles: String?) : UserDetailsDTO?
    fun addUserRole(username: String, role: String)
    fun removeUserRole(username: String, role: String)
    fun enableUser(username: String)
    fun disableUser(username: String)
    fun loadUserByUsername(username:String) : UserDetailsDTO
}