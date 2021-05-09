package com.wa2.demo.dto

import com.wa2.demo.domain.User

data class UserDTO (
    var userId: Long? = null,
    var username: String? = null,
    var password: String? = null,
    var email: String? = null,
    var isEnabled: Boolean = false,
    var roles: String? = null
) {
    fun toUserEntity(): User {
        val user = User()
        user.userId = userId
        user.username = username
        user.password = password
        user.email = email
        user.isEnabled = isEnabled
        user.roles = roles
        return user
    }
}

fun User.toUserDTO(): UserDTO = UserDTO(
    userId,
    username,
    password,
    email,
    isEnabled,
    roles
)