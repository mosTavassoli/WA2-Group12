package com.wa2.demo.domain

import java.sql.Timestamp
import java.util.*
import javax.persistence.*


@Entity
class EmailVerificationToken {


    @Id
    var token: UUID? = null

    @Column
    var username: String? = null

    @Column
    var expirationTimestamp: Long? =  null

}