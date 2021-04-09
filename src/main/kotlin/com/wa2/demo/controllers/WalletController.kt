package com.wa2.demo.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping

@Controller
class WalletController {

    @PostMapping("/wallet")
    fun createWallet(){

        //TODO create Wallet
        println("Creating wallet...")


    }



    @GetMapping("/wallet/{walletId}/transaction")
    fun createTransaction(@PathVariable walletId: Long) {
        //TODO
        println("Got the message!")
    }






}