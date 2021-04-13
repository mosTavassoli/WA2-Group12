package com.wa2.demo.entities

import org.springframework.data.annotation.CreatedDate
import java.math.BigDecimal
import java.util.*
import javax.persistence.*

@Entity
class Transaction(
    @Id
    @GeneratedValue
    var transactionId: Long? = null,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "payeeId", referencedColumnName = "walletId")
    var payeeWallet: Wallet? = null,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "payerId", referencedColumnName = "walletId")
    var payerWallet: Wallet? = null,

    @Column
    @CreatedDate // TODO
    var dateTime: Date? = null,

    @Column
    var amount: BigDecimal? = null
)