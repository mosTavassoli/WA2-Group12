package com.wa2.demo.controllers

import com.wa2.demo.dto.TransactionDTO
import com.wa2.demo.services.TransactionService
import org.springframework.beans.factory.annotation.Autowired

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/wallet")
class WalletController(val transactionService: TransactionService) {

    @PostMapping("/wallet")
    fun createWallet() {

        //TODO create Wallet
        println("Creating wallet...")


    }


    @PostMapping("/{walletId}/transaction")
    fun createTransaction(@PathVariable walletId: Long, @RequestBody transactionDTO: TransactionDTO) {
        // TODO get Transaction and pass it to Transaction Service
//        println("amount: $transactionDTO")
//        transactionService.createTransaction(transactionDTO)
    }


}