package com.wa2.demo.domain


import com.fasterxml.jackson.annotation.JsonBackReference
import java.math.BigDecimal
import java.util.*
import javax.persistence.*

@Entity
class Transaction {
    @Id
    @GeneratedValue
    var transactionId: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "payeeId", referencedColumnName = "walletId")
    var payeeWallet: Wallet? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "payerId", referencedColumnName = "walletId")
    var payerWallet: Wallet? = null

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    var dateTime: Date? = null

    @Column
    var amount: BigDecimal? = null
}