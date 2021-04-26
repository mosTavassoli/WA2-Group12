package com.wa2.demo.dto

import org.springframework.security.core.GrantedAuthority

interface UserDetails {
    fun getPassword(): String?
    fun getUsername(): String?
    fun isEnabled(): Boolean
    //fun getAuthorities(): MutableCollection<out GrantedAuthority>
    //fun isAccountNonExpired(): Boolean
    //fun isAccountNonLocked(): Boolean
    //fun isCredentialsNonExpired(): Boolean
}