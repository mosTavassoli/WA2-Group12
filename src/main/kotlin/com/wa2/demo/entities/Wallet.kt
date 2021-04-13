package com.wa2.demo.entities

import org.springframework.hateoas.RepresentationModel
import javax.persistence.*

//@Entity
//class Wallet: RepresentationModel<Wallet>() {
//    @Id
//    @GeneratedValue(strategy= GenerationType.AUTO)
//    var walletId: Long? = null
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "customerId", referencedColumnName = "customerId")
//    val customer: Customer? = null
//
//    @Column
//    var currentAmount: Long? = null
//
//    @OneToMany(mappedBy = "payee", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
//    lateinit var payeeId: MutableList<Transaction>
//
//    @OneToMany(mappedBy = "payer", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
//    lateinit var payerId: MutableList<Transaction>
//}

@Entity
class Wallet(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var walletId: Long? = null,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customerId", referencedColumnName = "customerId")
    val customer: Customer? = null,

    @Column
    var currentAmount: Long? = null,

    @OneToMany(mappedBy = "payee", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    var payeeId: MutableSet<Transaction>,

    @OneToMany(mappedBy = "payer", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    var payerId: MutableSet<Transaction>
)

