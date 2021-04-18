package com.wa2.demo.controllers

import com.google.gson.*
import com.wa2.demo.dto.CustomerDTO
import com.wa2.demo.dto.TransactionDTO
import com.wa2.demo.dto.WalletDTO
import com.wa2.demo.domain.Transaction
import com.wa2.demo.dto.toTransactionDTO
import com.wa2.demo.services.TransactionService
import com.wa2.demo.services.WalletService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid


@RestController
@RequestMapping("/wallet")
class WalletController(
    @Autowired val transactionService: TransactionService,
    @Autowired val walletService: WalletService
) {

    @PostMapping()
    fun createWallet(@RequestBody @Valid body: String): ResponseEntity<String> {
        return try {
            val item: JsonObject = Gson().fromJson(body, JsonObject::class.java)
            val wallet: WalletDTO = walletService.addNewWallet(item.get("customerId").asLong)
            val walletItem = Gson().toJson(wallet)
            ResponseEntity<String>(walletItem, HttpStatus.CREATED)
        } catch (ex: java.lang.Exception) {
            ResponseEntity<String>(ex.message.toString(), HttpStatus.BAD_REQUEST)
        }
    }

    @GetMapping("/{walletId}")
    fun getWalletDetails(@PathVariable walletId: Long): ResponseEntity<String>{
        return try {
            val result = Gson().toJson(walletService.getWalletById(walletId))
            ResponseEntity<String>(result, HttpStatus.CREATED)
        } catch (ex: java.lang.Exception) {
            ResponseEntity<String>(ex.message.toString(), HttpStatus.BAD_REQUEST)
        }
    }

    @PostMapping("/{walletId}/transactions", MediaType.APPLICATION_JSON_VALUE)
    fun createTransaction(@PathVariable walletId: Long, @RequestBody body: String): ResponseEntity<String> {
        return try {
            val item: JsonObject = Gson().fromJson(body, JsonObject::class.java)
            var payer = WalletDTO()
            var payee = WalletDTO()
            var transactionDTO = TransactionDTO()
            if (!item.get("payer").isJsonNull) {
                payer = WalletDTO(item.get("payer").asLong)
                payee = WalletDTO(walletId)

                transactionDTO = TransactionDTO(
                    null, payee, payer, Date(),
                    item.get("amount").asBigDecimal
                )
            }
            ResponseEntity<String>(transactionService.createTransaction(transactionDTO).toString(), HttpStatus.CREATED)
        } catch (ex: Exception) {
            ResponseEntity<String>(ex.message.toString(), HttpStatus.BAD_REQUEST)
        }
    }

    @GetMapping("/{walletId}/transactions", MediaType.APPLICATION_JSON_VALUE)
    fun getTransactionByDateTime(
        @PathVariable walletId: Long,
        @Valid @RequestParam(name = "from") startDate: Long,
        @Valid @RequestParam(name = "to") endDate: Long
    ): ResponseEntity<String> {
        return try {
            ResponseEntity(
                Gson().toJson(transactionService.transactionsByDate(walletId, startDate, endDate)),
                HttpStatus.OK
            )
        } catch (ex: Exception) {
            ResponseEntity.badRequest().body("Some parameters are invalid!")
        }
    }

    @GetMapping("/{walletId}/transactions/{transactionId}")
    fun getTransactionDetails(
        @PathVariable walletId: Long,
        @PathVariable transactionId: Long
    ): ResponseEntity<String> {
        return try {
            ResponseEntity<String>(Gson().toJson(transactionService.getTransactionDetails(walletId, transactionId)?.toTransactionDTO()), HttpStatus.OK)
        } catch (ex: Exception) {
            ResponseEntity<String>(ex.message.toString(), HttpStatus.BAD_REQUEST)
        }
    }
}