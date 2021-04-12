package com.wa2.demo.entities

import org.springframework.hateoas.RepresentationModel
import javax.persistence.*

//@Entity
//class Customer : RepresentationModel<Customer>() {
//    @Id
//    @GeneratedValue(strategy= GenerationType.AUTO)
//    var customerId: Long? = null
//
//    @Column(length = 100)
//    var name: String? = null
//
//    @Column(length = 150)
//    var surname: String? = null
//
//    @Column(length = 250)
//    var deliveryAddress: String? = null
//
//    @Column(length = 100)
//    var email: String? = null
//
//    @OneToMany(mappedBy = "customer", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
//    lateinit var wallets: MutableList<Wallet>
//}

@Entity
class Customer(@Id
               @GeneratedValue(strategy= GenerationType.AUTO)
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
               var wallets: MutableList<Wallet>)

