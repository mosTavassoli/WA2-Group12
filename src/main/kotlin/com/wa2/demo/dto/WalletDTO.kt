package com.wa2.demo.dto


data class WalletDTO(
    var walletId : Long,
    var customerId: Long,
    var currentAmount : Long,
    var payeeId: MutableList<TransactionDTO>,
    var payerId: MutableList<TransactionDTO>
)
