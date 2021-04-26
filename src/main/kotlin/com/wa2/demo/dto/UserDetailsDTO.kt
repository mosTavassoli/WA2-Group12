package com.wa2.demo.dto

import com.wa2.demo.domain.User

class UserDetailsDTO(userId: Long?, username: String?, password: String?, email: String?, isEnabled: Boolean, roles: String?)
    : UserDetails
{
    var userId: Long? = null
    var _username: String? = null
    var _password: String? = null
    var email: String? = null
    var _isEnabled: Boolean = false
    var roles: String? = null

    override fun getPassword(): String? {
        return _password
    }

    override fun getUsername(): String? {
        return _username
    }

    override fun isEnabled(): Boolean {
        return _isEnabled
    }

    /*

    override fun isCredentialsNonExpired(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isAccountNonLocked(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        TODO("Not yet implemented")
    }

    override fun isAccountNonExpired(): Boolean {
        TODO("Not yet implemented")
    }

     */

}

fun User.toUserDetailsDTO(): UserDetailsDTO = UserDetailsDTO(
        userId,
        username,
        password,
        email,
        isEnabled,
        roles
)