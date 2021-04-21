package com.wa2.demo.domain

import com.wa2.demo.utils.RoleNames
import javax.persistence.*
import javax.validation.constraints
import com.sun.istack.internal.*

@Entity
@Table(indexes = [Index(name = "index", columnList = "username", unique = true)])
class User {

    @Id
    @GeneratedValue
    var userId: Long? = null

    //TODO check validated
    @NotNull
    //@Validated
    @Column(length = 100, unique = true, nullable = false)
    var username: String? = null

    @Column(length = 100)
    var password: String? = null

    @Column(length = 100, unique = true)
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

