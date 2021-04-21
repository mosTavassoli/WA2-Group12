package com.wa2.demo.domain

import com.wa2.demo.utils.RoleNames
import javax.persistence.*

@Entity
class User {

    @Id
    @GeneratedValue
    var userId: Long? = null

    @Column(length = 100)
    var username: String? = null

    @Column(length = 100)
    var password: String? = null

    @Column(length = 100)
    var email: String? = null

    @Column
    var isEnabled: Boolean = false

    @Column
    lateinit var roles: MutableSet<RoleNames>


    fun addRole(role:RoleNames){
        if(!roles.contains(role))
            roles.add(role)
    }

    fun removeRole(role:RoleNames){
        if(roles.contains(role))
            roles.remove(role)
    }
}

