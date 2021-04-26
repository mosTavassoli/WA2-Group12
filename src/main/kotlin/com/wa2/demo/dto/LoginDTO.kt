package com.wa2.demo.dto

import com.sun.istack.NotNull
import javax.validation.constraints.NotBlank

class LoginDTO(
    @field:NotBlank
    var username: String,
    @field:NotBlank
    var password: String
)