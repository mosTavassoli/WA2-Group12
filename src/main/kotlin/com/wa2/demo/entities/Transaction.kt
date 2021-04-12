package com.wa2.demo.entities

import org.springframework.hateoas.RepresentationModel
import java.util.*
import javax.persistence.*

//@Entity
//class Transaction: RepresentationModel<Transaction>() {
//    @Id
//    @GeneratedValue
//    var transactionId: Long? = null
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "payeeId", referencedColumnName = "walletId")
//    var payee: Wallet? = null
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "payerId", referencedColumnName = "walletId")
//    var payer: Wallet? = null
//
//    @Column
//    var dateTime: Date? = null
//
//    @Column
//    var amount: Long? = null
//}

@Entity
class Transaction(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var transactionId: Long? = null,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "payeeId", referencedColumnName = "walletId")
    var payee: Wallet? = null,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "payerId", referencedColumnName = "walletId")
    var payer: Wallet? = null,

    @Column
    var dateTime: Date? = null,

    @Column
    var amount: Long? = null
)