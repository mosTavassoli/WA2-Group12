package com.wa2.demo.domain

import com.fasterxml.jackson.annotation.JsonBackReference
import javax.persistence.*

@Entity
class Customer {
    @Id
    @GeneratedValue
    var customerId: Long? = null

    @Column(length = 100)
    var name: String? = null

    @Column(length = 150)
    var surname: String? = null

    @Column(length = 250)
    var deliveryAddress: String? = null

    @Column(length = 100)
    var email: String? = null

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, targetEntity = Wallet::class)
    lateinit var wallets: MutableSet<Wallet>

    @OneToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    var user: User? = null
}

