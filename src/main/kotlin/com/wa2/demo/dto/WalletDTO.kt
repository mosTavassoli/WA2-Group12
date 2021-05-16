package com.wa2.demo.dto

import com.wa2.demo.domain.Wallet
import java.math.BigDecimal


data class WalletDTO(
    var walletId: Long? = null,
    var customer: CustomerDTO? = null,
    var currentAmount: BigDecimal? = null,
    var payees: MutableSet<TransactionDTO> = mutableSetOf(),
    var payers: MutableSet<TransactionDTO> = mutableSetOf()
) {
    fun toWalletEntity(): Wallet {
        val wallet = Wallet()
        wallet.walletId = walletId
        wallet.currentAmount = currentAmount
        wallet.payees = payees.map { a -> a.toTransactionEntity() }.toMutableSet()
        wallet.payers = payers.map { a -> a.toTransactionEntity() }.toMutableSet()
        return wallet
    }
}

fun Wallet.toWalletDTO(): WalletDTO = WalletDTO(
    walletId,
    customer?.toCustomerDTO(),
    currentAmount,
    mutableSetOf(),
    mutableSetOf()
)