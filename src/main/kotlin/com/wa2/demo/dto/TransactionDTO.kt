package com.wa2.demo.dto

import java.util.*

data class TransactionDTO(
    var transactionId: Long,
    var payee: WalletDTO,
    var payer: WalletDTO,
    var dateTime: Date,
    var amount: Long
)
