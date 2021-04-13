package com.wa2.demo.entities

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

    @OneToMany(mappedBy = "customer", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    var wallets: MutableSet<Wallet>
)

