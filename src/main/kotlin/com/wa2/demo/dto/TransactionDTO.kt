package com.wa2.demo.dto

import com.wa2.demo.entities.Transaction
import java.math.BigDecimal
import java.util.*

data class TransactionDTO(
    var transactionId: Long? = null,
    var payeeWallet: WalletDTO? = null,
    var payerWallet: WalletDTO? = null,
    var dateTime: Date? = null,
    var amount: BigDecimal? = null
) {
    // TODO Create extension Function , toTransaction
    fun toTransactionEntity(): Transaction = Transaction(
        transactionId,
        payeeWallet?.toWalletEntity(),
        payerWallet?.toWalletEntity(),
        dateTime,
        amount
    )
}