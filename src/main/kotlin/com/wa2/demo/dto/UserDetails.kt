package com.wa2.demo.dto

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails


interface UserDetails : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority>
    override fun getPassword(): String
    override fun getUsername(): String
    override fun isAccountNonExpired(): Boolean
    override fun isAccountNonLocked(): Boolean
    override fun isCredentialsNonExpired(): Boolean
    override fun isEnabled(): Boolean

    //    fun getPassword(): String?
//    fun getUsername(): String?
//    fun isEnabled(): Boolean
    //fun getAuthorities(): MutableCollection<out GrantedAuthority>
    //fun isAccountNonExpired(): Boolean
    //fun isAccountNonLocked(): Boolean
    //fun isCredentialsNonExpired(): Boolean
}