package com.wa2.demo.dto

import com.wa2.demo.domain.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils


class UserDetailsDTO : UserDetails {

    var _password: String? = null
    var _username: String? = null
    var userId: Long? = null
    var email: String? = null
    var roles: String? = null

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return AuthorityUtils.createAuthorityList("USER")
    }

    override fun getPassword(): String {
        return _password!!
    }

    override fun getUsername(): String {
        return _username!!
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}

fun User.toUserDetailsDTO(): UserDetailsDTO {
    val ud = UserDetailsDTO()
    ud._username = username
    ud._password = password
    ud.userId = userId
    ud.email = email
    ud.roles = roles
    return ud
}