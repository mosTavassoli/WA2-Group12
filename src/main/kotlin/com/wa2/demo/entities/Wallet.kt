package com.wa2.demo.entities

import java.math.BigDecimal
import javax.persistence.*

@Entity
class Wallet(
    @Id
    @GeneratedValue
    var walletId: Long? = null,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customerId", referencedColumnName = "customerId")
    val customer: Customer? = null,

    @Column
    var currentAmount: BigDecimal? = null,

    @OneToMany(mappedBy = "payeeWallet", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    var payees: MutableSet<Transaction>,

    @OneToMany(mappedBy = "payerWallet", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    var payers: MutableSet<Transaction>
)

