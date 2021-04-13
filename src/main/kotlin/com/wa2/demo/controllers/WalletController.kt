package com.wa2.demo.controllers

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.wa2.demo.dto.TransactionDTO
import com.wa2.demo.dto.WalletDTO
import com.wa2.demo.services.TransactionService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder.json
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


    @PostMapping("/{walletId}/transaction", MediaType.APPLICATION_JSON_VALUE)
    fun createTransaction(@PathVariable walletId: Long, @RequestBody body: String) {
        val convertedObject: JsonObject = Gson().fromJson(body, JsonObject::class.java)
        println("body: $body")
        println("Json: ${convertedObject.get("payer")}")
        val payer = WalletDTO(
            convertedObject.get("payer").asLong
        )
        val payee = WalletDTO(
            walletId
        )
        val transactionDTO = TransactionDTO(
            null,
            payee,
            payer,
            null,
            convertedObject.get("amount").asBigDecimal
        )
        transactionService.createTransaction(transactionDTO)
    }
}
//@RequestBody body: Map<String, BigDecimal>

/*
* {
        "payee": {
            "walletId" : 1,
            "customer" : {
                "customerId" : 1
            }
        },
        "payer": {
            "walletId" : 1,
             "customer" : {
                "customerId" : 1
            }
        },
        "dateTime" : "2021-04-09",
      "amount" : 60
}
* */