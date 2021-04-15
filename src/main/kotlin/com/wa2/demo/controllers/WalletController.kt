package com.wa2.demo.controllers

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.wa2.demo.dto.CustomerDTO
import com.wa2.demo.dto.TransactionDTO
import com.wa2.demo.dto.WalletDTO
import com.wa2.demo.domain.Transaction
import com.wa2.demo.services.TransactionService
import com.wa2.demo.services.WalletService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid


@RestController
@RequestMapping("/wallet")
class WalletController(val transactionService: TransactionService, val walletService: WalletService) {

    @PostMapping()
    fun createWallet(@RequestBody @Valid customerDTO: CustomerDTO): ResponseEntity<String> {
        val wallet: WalletDTO = walletService.addNewWallet(customerDTO)
        val walletItem = Gson().toJson(wallet)
        return ResponseEntity<String>(walletItem, HttpStatus.CREATED)
    }

    @PostMapping("/{walletId}/transaction", MediaType.APPLICATION_JSON_VALUE)
    fun createTransaction(@PathVariable walletId: Long, @RequestBody body: String) {
        val item: JsonObject = Gson().fromJson(body, JsonObject::class.java)
        if (!item.get("payer").isJsonNull) {
            val payer = WalletDTO(item.get("payer").asLong)
            val payee = WalletDTO(walletId)
            val transactionDTO = TransactionDTO(
                null, payee, payer, Date(),
                item.get("amount").asBigDecimal
            )
            transactionService.createTransaction(transactionDTO)
        }

    }

    @GetMapping("/{walletId}/transactions", MediaType.APPLICATION_JSON_VALUE)
    fun getTransactionByDateTime(
        @PathVariable walletId: Long,
        @RequestParam(name = "from") startDate: Long,
        @RequestParam(name = "to") endDate: Long
    ): List<Transaction>? {
        return transactionService.transactionsByDate(walletId, startDate, endDate)
    }
}