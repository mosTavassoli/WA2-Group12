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


//class UserDetailsDTO(userId: Long?, username: String?, password: String?, email: String?, isEnabled: Boolean, roles: String?)
//    : UserDetails
//{
//    var userId: Long? = null
//    var _username: String? = null
//    var _password: String? = null
//    var email: String? = null
//    var _isEnabled: Boolean = false
//    var roles: String? = null
//
//    override fun getPassword(): String? {
//        return _password
//    }
//
//    override fun getUsername(): String? {
//        return _username
//    }
//
//    override fun isEnabled(): Boolean {
//        return _isEnabled
//    }
//
//    /*
//
//    override fun isCredentialsNonExpired(): Boolean {
//        TODO("Not yet implemented")
//    }
//
//    override fun isAccountNonLocked(): Boolean {
//        TODO("Not yet implemented")
//    }
//
//    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
//        TODO("Not yet implemented")
//    }
//
//    override fun isAccountNonExpired(): Boolean {
//        TODO("Not yet implemented")
//    }
//
//     */
//
//}
//
//fun User.toUserDetailsDTO(): UserDetailsDTO = UserDetailsDTO(
//        userId,
//        username,
//        password,
//        email,
//        isEnabled,
//        roles
//)