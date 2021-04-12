
package com.wa2.demo.dto

import com.wa2.demo.entities.Wallet


data class WalletDTO(
    var walletId: Long? = null,
    var customer: CustomerDTO?= null,
    var currentAmount: Long?= null,
//    var payeeId: MutableSet<TransactionDTO>? = null ,
//    var payerId: MutableSet<TransactionDTO> = mutableSetOf<TransactionDTO>()
){
    fun toWalletEntity(): Wallet = Wallet(
        walletId,
        customer?.toCustomerEntity(),
        currentAmount,
        )
}
fun Wallet.toWalletDTO():WalletDTO = WalletDTO (
            walletId,customer?.toCustomerDTO(),currentAmount
        )