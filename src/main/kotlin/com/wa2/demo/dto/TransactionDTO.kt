package com.wa2.demo.dto

import com.wa2.demo.domain.Transaction
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
    fun toTransactionEntity(): Transaction {
        val transaction = Transaction()
        transaction.transactionId = transactionId
        transaction.payeeWallet = payeeWallet?.toWalletEntity()
        transaction.payerWallet = payerWallet?.toWalletEntity()
        transaction.dateTime = dateTime
        transaction.amount = amount
        return transaction
    }
}

fun Transaction.toTransactionDTO(): TransactionDTO = TransactionDTO(
    transactionId,
    payeeWallet?.toWalletDTO(),
    payerWallet?.toWalletDTO(),
    dateTime,
    amount
)