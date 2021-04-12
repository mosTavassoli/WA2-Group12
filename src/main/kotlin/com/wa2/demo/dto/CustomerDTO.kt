package com.wa2.demo.dto

import com.wa2.demo.entities.Customer


data class CustomerDTO(
    var customerId: Long? = null,
    var customerName: String? = null,
    var customerSurname: String? = null,
    var deliveryAddress: String? = null,
    var email: String? = null,
    var wallets: MutableSet<WalletDTO> = mutableSetOf<WalletDTO>()
){
    fun toCustomerEntity(): Customer = Customer(
        customerId,
        customerName,
        customerSurname,
        deliveryAddress,
        email,
        mutableListOf()
    )
}
fun Customer.toCustomerDTO():CustomerDTO = CustomerDTO (
    customerId,
    name,
    surname,
    deliveryAddress,
    email,
    mutableSetOf()
)