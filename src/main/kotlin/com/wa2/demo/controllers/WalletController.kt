package com.wa2.demo.controllers

import com.wa2.demo.dto.TransactionDTO
import com.wa2.demo.services.TransactionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity

import org.springframework.web.bind.annotation.*
import java.math.BigDecimal

@RestController
@RequestMapping("/wallet")
class WalletController(val transactionService: TransactionService) {

    @PostMapping()
    fun createWallet(): ResponseEntity<String> {

        //TODO connect service
        println("Creating wallet...")


        return ResponseEntity<String>("Wallet created!", HttpStatus.CREATED)


    }


    @PostMapping("/{walletId}/transaction",MediaType.APPLICATION_JSON_VALUE)
    fun createTransaction(@PathVariable walletId: Long, @RequestBody transactionDTO: TransactionDTO) {
        transactionService.createTransaction(transactionDTO)
//        println("amount: ${body["amount"]}")
    }
}

//@RequestBody body: Map<String, BigDecimal>