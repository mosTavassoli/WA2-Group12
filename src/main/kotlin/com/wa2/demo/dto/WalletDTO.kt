package com.wa2.demo.dto

import com.wa2.demo.entities.Wallet


data class WalletDTO(
    var walletId: Long? = null,
    var customer: CustomerDTO? = null,
    var currentAmount: Long? = null,
    var payees: MutableSet<TransactionDTO> = mutableSetOf(),
    var payers: MutableSet<TransactionDTO> = mutableSetOf()
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