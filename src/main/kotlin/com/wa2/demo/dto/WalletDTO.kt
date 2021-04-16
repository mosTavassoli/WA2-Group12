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
    fun toWalletEntity(): Wallet = Wallet(
        walletId,
        customer?.toCustomerEntity(),
        currentAmount,
        mutableSetOf(),
        mutableSetOf()
    )

    companion object {
        fun getByWallet(wallet: Wallet): WalletDTO {

            var payeeId: MutableSet<TransactionDTO> = mutableSetOf<TransactionDTO>()
            for (itm in wallet.payees) {
                payeeId.add(TransactionDTO.getByTransaction(itm))
            }

            var payerId: MutableSet<TransactionDTO> = mutableSetOf<TransactionDTO>()
            for (itm in wallet.payers) {
                payerId.add(TransactionDTO.getByTransaction(itm))
            }

            return WalletDTO(
                walletId = wallet.walletId,
                customer = wallet.customer?.let { CustomerDTO.getByCustomer(it) },
                currentAmount = wallet.currentAmount,
                payees = payeeId,
                payers = payerId
            )
        }
    }
}

fun Wallet.toWalletDTO(): WalletDTO = WalletDTO(
    walletId,
    customer?.toCustomerDTO(),
    currentAmount,
    mutableSetOf(),
    mutableSetOf()
)