package com.wa2.demo.dto

import com.wa2.demo.entities.Customer


data class CustomerDTO(
    var customerId: Long? = null,
    var name: String? = null,
    var surname: String? = null,
    var deliveryAddress: String? = null,
    var email: String? = null,
    var wallets: MutableSet<WalletDTO> = mutableSetOf<WalletDTO>()
) {
    fun toCustomerEntity(): Customer = Customer(
        customerId,
        name,
        surname,
        deliveryAddress,
        email,
        mutableSetOf()
    )
}

fun Customer.toCustomerDTO(): CustomerDTO = CustomerDTO(
    customerId,
    name,
    surname,
    deliveryAddress,
    email,
    mutableSetOf()
)