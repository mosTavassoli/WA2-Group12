package com.wa2.demo.dto

import com.wa2.demo.entities.Wallet


data class WalletDTO(
    var walletId: Long? = null,
    var customer: CustomerDTO? = null,
    var currentAmount: Long? = null,
    var payee: MutableSet<TransactionDTO>? = mutableSetOf<TransactionDTO>(),
    var payer: MutableSet<TransactionDTO> = mutableSetOf<TransactionDTO>()
) {
    fun toWalletEntity(): Wallet = Wallet(
        walletId,
        customer?.toCustomerEntity(),
        currentAmount,
        mutableSetOf(),
        mutableSetOf()
    )
}

fun Wallet.toWalletDTO(): WalletDTO = WalletDTO(
    walletId,
    customer?.toCustomerDTO(),
    currentAmount,
    mutableSetOf(),
    mutableSetOf()
)