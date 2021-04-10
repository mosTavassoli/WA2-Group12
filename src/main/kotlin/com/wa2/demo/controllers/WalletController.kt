package com.wa2.demo.controllers

import com.wa2.demo.dto.TransactionDTO
import com.wa2.demo.services.TransactionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/wallet")
class WalletController(val transactionService: TransactionService) {

    @PostMapping()
    fun createWallet(): ResponseEntity<String> {

        //TODO connect service
        println("Creating wallet...")


        return ResponseEntity<String>("Wallet created!", HttpStatus.CREATED)


    }


    @PostMapping("/{walletId}/transaction")
    fun createTransaction(@PathVariable walletId: Long, @RequestBody transactionDTO: TransactionDTO) {
        // TODO get Transaction and pass it to Transaction Service
//        println("amount: $transactionDTO")
//        transactionService.createTransaction(transactionDTO)
    }


}