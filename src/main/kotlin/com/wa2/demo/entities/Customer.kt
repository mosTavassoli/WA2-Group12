package com.wa2.demo.entities

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.*

@Entity
class Customer(
    @Id
    @GeneratedValue
    var customerId: Long? = null,

    @Column(length = 100)
    var name: String? = null,

    @Column(length = 150)
    var surname: String? = null,

    @Column(length = 250)
    var deliveryAddress: String? = null,

    @Column(length = 100)
    var email: String? = null,

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, targetEntity = Wallet::class)
    var wallets: MutableSet<Wallet>
)

