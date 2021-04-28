package com.wa2.demo.controllers

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.wa2.demo.dto.LoginDTO
import com.wa2.demo.dto.UserDetailsDTO
import com.wa2.demo.services.UserDetailsService
import com.wa2.demo.utils.Constants
import com.wa2.demo.utils.RoleNames
import net.minidev.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
class AuthenticationController {


    @Autowired lateinit var UserDetailsService : UserDetailsService


    val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})";

    @PostMapping(Constants.REGISTER)
    fun register(@RequestBody @Valid body : String) : ResponseEntity<String> {

        val item : JsonObject = Gson().fromJson( body, JsonObject::class.java )

        if(checkRegistrationRequest(item)){

            println("Request OK!")

            var UserDetailsDTO : UserDetailsDTO? = UserDetailsService.addUser(
                item.get("username").toString(),
                item.get("password").toString(),
                item.get("email").toString(),
                false,
                listOf<RoleNames>(RoleNames.CUSTOMER)
            )
            if(UserDetailsDTO != null)
                return ResponseEntity( Gson().toJson(UserDetailsDTO), HttpStatus.CREATED )
            else
                return ResponseEntity( Gson().toJson("Username or email already in use"), HttpStatus.CONFLICT)
        }
        else {
            println("Request not OK")
            return ResponseEntity(Gson().toJson("Request not valid"),HttpStatus.BAD_REQUEST)
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
//            ResponseEntity<String>(
//                Gson().toJson(
//                    userDetailsService.loadUserByUsername(
//                        loginDTO.username
//                    )
//                ), HttpStatus.OK
//            )
            val user = UserDetailsService.loadUserByUsername(loginDTO.username)
            //TODO ---> check the user pass, if Ok continue
//            if (!user.comparePassword(loginDTO.password)){
//                return ResponseEntity.badRequest().body("Invalid Password")
//            }


//            val authentication: Authentication = authenticationManager.authenticate(
//                UsernamePasswordAuthenticationToken(
//                    loginDTO.username,
//                    loginDTO.password
//                )
//            )
//            SecurityContextHolder.getContext().setAuthentication(authentication)
//            val jwt : String = jwtUtils.generateJwtToken(authentication)
//
//
//            return ResponseEntity(Gson().toJson(jwt),HttpStatus.OK)

            return null
        } catch (ex: Exception) {
            ResponseEntity<String>(ex.message.toString(), HttpStatus.BAD_REQUEST)
        }
    }


    @GetMapping(Constants.REGISTRATION_CONFORMATION)
    fun registrationConfirmation(){





    }

    fun checkRegistrationRequest(item: JsonObject) : Boolean {

        if(item.isJsonNull)
            return false
        if( item.get("username") == null )
            return false
        if( item.get("email") == null || !checkEmail(item.get("email").toString()) )
            return false
        if( item.get("name") == null )
            return false
        if( item.get("surname") == null )
            return false
        if( item.get("address") == null )
            return false
        if( item.get("password") == null )
            return false
        if( item.get("confirmPassword") == null )
            return false
        if( item.get("password") != item.get("confirmPassword") )
            return false

        return true

    }

    fun checkEmail(email: String) : Boolean {

        println( "Email is valid:" + EMAIL_REGEX.toRegex().matches(email) )
        //TODO(Make regex work!)
//        return EMAIL_REGEX.toRegex().matches(email)
        return true

    }


}