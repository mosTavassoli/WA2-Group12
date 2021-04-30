package com.wa2.demo.controllers

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.wa2.demo.dto.LoginDTO
import com.wa2.demo.dto.UserDetailsDTO
import com.wa2.demo.security.JwtUtils
import com.wa2.demo.services.UserDetailsService
import com.wa2.demo.utils.Constants
import com.wa2.demo.utils.RoleNames
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid
import org.springframework.security.core.authority.SimpleGrantedAuthority
import java.util.ArrayList

import org.springframework.security.core.GrantedAuthority








@RestController
class AuthenticationController {

    @Autowired
    lateinit var authenticationManager: AuthenticationManager
    @Autowired
    lateinit var userDetailsService: UserDetailsService
    @Autowired
    lateinit var jwtUtils: JwtUtils


    val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})";

    @PostMapping(Constants.REGISTER)
    fun register(@RequestBody @Valid body: String): ResponseEntity<String> {

        val item: JsonObject = Gson().fromJson(body, JsonObject::class.java)

        if (checkRegistrationRequest(item)) {

            println("Request OK!")

            var userDetailsDTO: UserDetailsDTO? = userDetailsService.addUser(
                item.get("username").toString(),
                item.get("password").toString(),
                item.get("email").toString(),
                false,
                listOf<RoleNames>(RoleNames.CUSTOMER)
            )
            if (userDetailsDTO != null)
                return ResponseEntity(Gson().toJson(userDetailsDTO), HttpStatus.CREATED)
            else
                return ResponseEntity(Gson().toJson("Username or email already in use"), HttpStatus.CONFLICT)
        } else {
            println("Request not OK")
            return ResponseEntity(Gson().toJson("Request not valid"), HttpStatus.BAD_REQUEST)
        }

        println(item)
        println(item.get("password") == null)


    }

    @PostMapping(Constants.SIGN_IN)
    fun authenticateUser(
        @RequestBody @Valid loginDTO: LoginDTO,
        bindingResult: BindingResult,
    ): ResponseEntity<String>? {
        return try {
            if (bindingResult.hasErrors()) return ResponseEntity.badRequest()
                .body("Username Or Password Must not be Null")

            val authentication: Authentication = authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                    loginDTO.username,
                    loginDTO.password
                )
            )
            SecurityContextHolder.getContext().authentication = authentication
            println("authorities: ${authentication.authorities}")
            val jwt: String = jwtUtils.generateJwtToken(authentication)
            println("$jwt")
            return ResponseEntity(Gson().toJson(jwt), HttpStatus.OK)


        } catch (ex: Exception) {
            ResponseEntity<String>(ex.message.toString(), HttpStatus.BAD_REQUEST)
        }
    }


    @GetMapping(Constants.REGISTRATION_CONFORMATION)
    fun registrationConfirmation(@PathVariable token: UUID) {
        userDetailsService.verifyToken(token)
    }

    fun checkRegistrationRequest(item: JsonObject): Boolean {

        if (item.isJsonNull)
            return false
        if (item.get("username") == null)
            return false
        if (item.get("email") == null || !checkEmail(item.get("email").toString()))
            return false
        if (item.get("name") == null)
            return false
        if (item.get("surname") == null)
            return false
        if (item.get("address") == null)
            return false
        if (item.get("password") == null)
            return false
        if (item.get("confirmPassword") == null)
            return false
        if (item.get("password") != item.get("confirmPassword"))
            return false

        return true

    }

    fun checkEmail(email: String): Boolean {
        println("Email is valid:" + EMAIL_REGEX.toRegex().matches(email))
        //TODO(Make regex work!)
//        return EMAIL_REGEX.toRegex().matches(email)
        return true
    }

    @PostMapping("/Authentication/enableUser")
    fun enableUser(@RequestParam(name = "username") username: String) {
//        val authorities =
//            SecurityContextHolder.getContext().authentication.authorities as Collection<SimpleGrantedAuthority>
        try {
            userDetailsService.enableUser(username)
        } catch (ex: AccessDeniedException) {
            throw ex
        }
    }

    @PostMapping("/Authentication/disableUser")
    fun disableUser(@RequestParam(name = "username") username: String) {
        userDetailsService.disableUser(username)
    }
}