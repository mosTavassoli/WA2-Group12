package com.wa2.demo.dto

import com.wa2.demo.entities.Transaction
import java.util.*

data class TransactionDTO(
    var transactionId: Long? = null,
    var payee: WalletDTO,
    var payer: WalletDTO,
    var dateTime: Date,
    var amount: Long
) {
    // TODO Create extension Function , toTransaction
//    fun toTransaction():Transaction = Transaction()
}
