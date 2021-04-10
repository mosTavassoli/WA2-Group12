package com.wa2.demo.dto

data class CustomerDTO(
    var customerId: Long? = null,
    var customerName: String? = null,
    var customerSurname: String? = null,
    var deliveryAddress: String? = null,
    var email: String? = null,
    var wallets: MutableSet<WalletDTO> = mutableSetOf<WalletDTO>()
)