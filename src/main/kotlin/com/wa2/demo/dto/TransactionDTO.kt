package com.wa2.demo.dto

import com.wa2.demo.entities.Transaction
import java.math.BigDecimal
import java.util.*
import javax.validation.constraints.Min

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

    companion object {
        fun getByTransaction(transaction: Transaction): TransactionDTO {

            var payee: WalletDTO? = transaction.payeeWallet?.let { WalletDTO.getByWallet(it) }
            var payer: WalletDTO? = transaction.payerWallet?.let { WalletDTO.getByWallet(it) }

            return TransactionDTO(
                transactionId = transaction.transactionId!!,
                payeeWallet = payee,
                payerWallet = payer,
                dateTime = transaction.dateTime,
                amount = transaction.amount
            )

        }
    }
}