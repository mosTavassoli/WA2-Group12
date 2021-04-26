package com.wa2.demo.services.impl

import com.wa2.demo.domain.User
import com.wa2.demo.dto.UserDetailsDTO
import com.wa2.demo.dto.toUserDetailsDTO
import com.wa2.demo.repositories.UserRepository
import com.wa2.demo.services.UserDetailsService
import com.wa2.demo.utils.RoleNames
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.Exception

@Service
@Transactional
class UserDetailServiceImpl(): UserDetailsService {

    @Autowired
    lateinit var userRepository: UserRepository

    override fun addUser(username: String, password: String, email: String, isEnabled: Boolean?, roles: List<RoleNames>?)
    : UserDetailsDTO? {
        try {
            var user = User()
            user.username = username
            user.password = password
            user.email = email
            if (isEnabled != null) {
                user.isEnabled = isEnabled
            }
            if(roles!=null) {
                for (role in roles) {
                    user.addRole(role)
                }
            }
            val repository = userRepository.save(user)
            return repository.toUserDetailsDTO()

        }catch (ex:Exception){
            ex.printStackTrace()
        }

        return null
    }

    override fun addUserRole(username: String, role: RoleNames) {
        try {

            var user = userRepository.findByUsername(username)
            if(user!=null){
                user.addRole(role)
                userRepository.save(user)
            }

        }catch (ex:Exception){
            ex.printStackTrace()
        }
    }

    override fun removeUserRole(username: String, role: RoleNames) {
        try {

            var user = userRepository.findByUsername(username)
            if(user!=null){
                user.removeRole(role)
                userRepository.save(user)
            }

        }catch (ex:Exception){
            ex.printStackTrace()
        }
    }

    override fun enableUser(username: String) {
        try {

            var user = userRepository.findByUsername(username)
            if(user!=null){
                user.isEnabled = true
                userRepository.save(user)
            }

        }catch (ex:Exception){
            ex.printStackTrace()
        }
    }

    override fun disableUser(username: String) {
        try {

            var user = userRepository.findByUsername(username)
            if(user!=null){
                user.isEnabled = false
                userRepository.save(user)
            }

        }catch (ex:Exception){
            ex.printStackTrace()
        }
    }

    override fun loadUserByUsername(username: String): UserDetailsDTO {

        var user = userRepository.findByUsername(username)
        if(user!=null){
            return user.toUserDetailsDTO()
        }
        else
            throw Exception("User not found")
    }
}