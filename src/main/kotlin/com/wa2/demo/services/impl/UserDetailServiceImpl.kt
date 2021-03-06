package com.wa2.demo.services.impl

import com.wa2.demo.domain.User
import com.wa2.demo.dto.CustomerDTO
import com.wa2.demo.dto.UserDetailsDTO
import com.wa2.demo.dto.toUserDTO
import com.wa2.demo.dto.toUserDetailsDTO
import com.wa2.demo.repositories.CustomerRepository
import com.wa2.demo.repositories.UserRepository
import com.wa2.demo.services.MailService
import com.wa2.demo.services.NotificationService
import com.wa2.demo.services.UserDetailsService
import com.wa2.demo.utils.RoleNames
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Role
import org.springframework.security.access.annotation.Secured
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.Exception
import java.util.*

@Service
@Transactional
class UserDetailServiceImpl(val passwordEncoder: PasswordEncoder) : UserDetailsService {

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var mailService: MailService

    @Autowired
    lateinit var notificationService: NotificationService

    @Autowired
    lateinit var customerRepository: CustomerRepository


    override fun addUser(
        username: String,
        password: String,
        email: String,
        isEnabled: Boolean?,
        roles: List<RoleNames>?,
        name: String,
        surname: String,
        address: String
    )
            : UserDetailsDTO? {
        try {
            val user = User()
            user.username = username.replace("\"","")
            user.password = passwordEncoder.encode(password)
            user.email = email.replace("\"","")
            if (isEnabled != null) {
                user.isEnabled = isEnabled
            }
            if (roles != null) {
                for (role in roles) {
                    user.addRole(role)
                }
            }

            //Save user details
            val repository = userRepository.save(user)

            addCustomer( CustomerDTO(
                name = name,
                surname = surname,
                deliveryAddress = address,
                email = email.replace("\"",""),
                user = repository.toUserDTO()



            ) )

            //Save verification token
            var token : UUID? = notificationService.saveToken(username)

            //Send verification token
            if (token != null) {
                mailService.sendMessage(email, token)
            }

            return repository.toUserDetailsDTO()

        } catch (ex: Exception) {
            ex.printStackTrace()
            return null
        }

        return null
    }

    override fun verifyToken(token: UUID) {
        try{
            var username : String? = notificationService.verifyToken(token)
            if(username != null ){
                enableUser(username)
                updateCustomerUserId(username)
            }


            else
                throw Exception("Token not found!")

        }catch(ex: Exception){
            ex.printStackTrace()
        }
    }

    private fun updateCustomerUserId(username: String) {
        try{
            val user = userRepository.findByUsername(username)
            val customer = customerRepository.findByEmail(user?.email!!)
            customer.user = user
            customerRepository.save(customer)
        }catch (ex:Exception){
            throw ex
        }
    }

    override fun addCustomer(customerDTO: CustomerDTO){

        customerRepository.save(customerDTO.toCustomerEntity())


    }




    override fun addUserRole(username: String, role: RoleNames) {
        try {
            val user = userRepository.findByUsername(username)
            if (user != null) {
                user.addRole(role)
                userRepository.save(user)
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    override fun removeUserRole(username: String, role: RoleNames) {
        try {

            val user = userRepository.findByUsername(username)
            if (user != null) {
                user.removeRole(role)
                userRepository.save(user)
            }

        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
//    @Secured("ROLE_ADMIN")
    override fun enableUser(username: String) {
        try {
            val user = userRepository.findByUsername(username)
            if (user != null) {
                user.isEnabled = true
                userRepository.save(user)
            }

        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    override fun disableUser(username: String) {
        try {
            val user = userRepository.findByUsername(username)
            if (user != null) {
                user.isEnabled = false
                userRepository.save(user)
            }

        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        val user = username?.let { userRepository.findByUsername(it) }
        if (user != null)
            return user.toUserDetailsDTO()
        else
            throw UsernameNotFoundException("User Not Found")
    }
}