package com.wa2.demo.dto

import com.wa2.demo.entities.Customer


data class CustomerDTO(
    var customerId: Long? = null,
    var name: String? = null,
    var surname: String? = null,
    var deliveryAddress: String? = null,
    var email: String? = null,
    var wallets: MutableSet<WalletDTO> = mutableSetOf()
) {
    fun toCustomerEntity(): Customer = Customer(
        customerId,
        name,
        surname,
        deliveryAddress,
        email,
        mutableSetOf()
    )

    companion object {
        fun getByCustomer(customer: Customer): CustomerDTO {

            var customerDTO = CustomerDTO()

            customerDTO.customerId = customer.customerId
            customerDTO.name = customer.name
            customerDTO.surname = customer.surname
            customerDTO.deliveryAddress = customer.deliveryAddress
            customerDTO.email = customer.email

            customerDTO.wallets = mutableSetOf<WalletDTO>()
            for (itm in customer.wallets)
                customerDTO.wallets.add(WalletDTO.getByWallet(itm))


            return customerDTO
        }
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