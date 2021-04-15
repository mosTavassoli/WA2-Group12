package com.wa2.demo.entities


import org.springframework.data.jpa.repository.Temporal
import java.math.BigDecimal
import java.util.*
import javax.persistence.*

@Entity
class Transaction(
    @Id
    @GeneratedValue
    var transactionId: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payeeId", referencedColumnName = "walletId")
    var payeeWallet: Wallet? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payerId", referencedColumnName = "walletId")
    var payerWallet: Wallet? = null,

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    var dateTime: Date?,

    @Column
    var amount: BigDecimal? = null
)