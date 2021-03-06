package com.wa2.demo.domain

import com.sun.istack.NotNull
import com.wa2.demo.utils.RoleNames
import javax.persistence.*
//import javax.validation.constraints

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
    var roles: String? = null


    fun addRole(inputRole:RoleNames){

        if(roles!=null) {
            var userRoles = roles!!.split(';')

            var found = false
            for(role in userRoles){
                if(role == inputRole.toString()){
                    found=true
                    break
                }
            }
            if(!found)
                roles+= ";$inputRole"
        }else{
            roles = inputRole.toString()
        }
    }

    fun removeRole(inputRole:RoleNames){

        if(roles!=null) {
            var userRoles = roles!!.split(';')

            var index = -1
            for(i in userRoles.indices){
                if(userRoles[i] == inputRole.toString()){
                    index = i
                    break
                }
            }
            if(index>-1) {
                userRoles.drop(index)
                roles = userRoles.joinToString(separator = ";")
            }
        }
    }
}
