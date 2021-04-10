package com.wa2.demo.dto


data class WalletDTO(
    var walletId: Long,
    var customer: CustomerDTO,
    var currentAmount: Long,
    var payeeId: MutableSet<TransactionDTO> = mutableSetOf<TransactionDTO>() ,
    var payerId: MutableSet<TransactionDTO> = mutableSetOf<TransactionDTO>()
)
