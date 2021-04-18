package com.wa2.demo.dto

import com.wa2.demo.domain.Customer
import com.wa2.demo.domain.Wallet


data class CustomerDTO(
    var customerId: Long? = null,
    var name: String? = null,
    var surname: String? = null,
    var deliveryAddress: String? = null,
    var email: String? = null,
    var wallets: MutableSet<WalletDTO> = mutableSetOf()
) {
    fun toCustomerEntity(): Customer {
        val customer = Customer()
        customer.customerId = customerId
        customer.deliveryAddress = deliveryAddress
        customer.email = email
        customer.name = name
        customer.surname = surname
        customer.wallets = wallets.map { a -> a.toWalletEntity() }.toMutableSet()
        return customer
    }
}

fun Customer.toCustomerDTO(): CustomerDTO = CustomerDTO(
    customerId,
    name,
    surname,
    deliveryAddress,
    email,
    mutableSetOf()
)