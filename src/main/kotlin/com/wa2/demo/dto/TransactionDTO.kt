package com.wa2.demo.dto

import com.wa2.demo.entities.Transaction
import java.math.BigDecimal
import java.util.*

data class TransactionDTO(
    var transactionId: Long? = null,
    var payee: WalletDTO? = null,
    var payer: WalletDTO? = null,
    var dateTime: Date? = null,
    var amount: BigDecimal? = null
) {
    // TODO Create extension Function , toTransaction
    fun toTransactionEntity(): Transaction = Transaction(
        transactionId,
        payee?.toWalletEntity(),
        payee?.toWalletEntity(),
        dateTime,
        amount
    )
}