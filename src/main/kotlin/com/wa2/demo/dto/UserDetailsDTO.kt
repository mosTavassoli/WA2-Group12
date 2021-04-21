package com.wa2.demo.dto

import com.wa2.demo.domain.User
import com.wa2.demo.domain.Wallet
import com.wa2.demo.utils.RoleNames

data class UserDetailsDTO(
        var userId: Long? = null,
        var username: String? = null,
        var password: String? = null,
        var email: String? = null,
        var isEnabled: Boolean = false,
        var roles: MutableSet<RoleNames> = mutableSetOf()
) {
    companion object {
        fun UsertoUserDetailsDTO(user: User): UserDetailsDTO? {

            return null
        }
    }
}

fun User.toUserDetailsDTO(): UserDetailsDTO = UserDetailsDTO(
        userId,
        username,
        password,
        email,
        isEnabled,
        roles
)