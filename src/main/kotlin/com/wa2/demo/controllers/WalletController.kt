package com.wa2.demo.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class WalletController {
    @GetMapping("/wallet/{walletId}/transaction")
    fun createTransaction(@PathVariable walletId: Long) {
        //TODO
    }
}