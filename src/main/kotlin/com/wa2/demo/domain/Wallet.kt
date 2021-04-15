package com.wa2.demo.domain

import com.fasterxml.jackson.annotation.JsonManagedReference
import java.math.BigDecimal
import javax.persistence.*

@Entity
class Wallet(
    @Id
    @GeneratedValue
    var walletId: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerId", referencedColumnName = "customerId")
    val customer: Customer? = null,

    @Column
    var currentAmount: BigDecimal? = null,

    @OneToMany(mappedBy = "payeeWallet", fetch = FetchType.LAZY, targetEntity = Transaction::class)
    @JsonManagedReference
    var payees: MutableSet<Transaction>,

    @OneToMany(mappedBy = "payerWallet", fetch = FetchType.LAZY, targetEntity =  Transaction::class)
    @JsonManagedReference
    var payers: MutableSet<Transaction>
)
