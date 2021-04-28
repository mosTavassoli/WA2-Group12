package com.wa2.demo.controllers

import com.wa2.demo.utils.Constants
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class AuthenticationController {

    @PostMapping(Constants.REGISTER)
    fun register(){




    }

    @PostMapping(Constants.SIGN_IN)
    fun signIn(){




    }


    @GetMapping(Constants.REGISTRATION_CONFORMATION)
    fun registrationConfirmation(){





    }





}