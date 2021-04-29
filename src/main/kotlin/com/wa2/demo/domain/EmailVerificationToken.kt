package com.wa2.demo.domain

import org.hibernate.annotations.Type
import java.sql.Timestamp
import java.util.*
import javax.persistence.*


@Entity
class EmailVerificationToken {


    @Id
//    @Column(columnDefinition = "uuid", updatable = false)
//    @Type(type="org.hibernate.type.UUIDCharType")
//    @Type(type="uuid-char")
    @Type(type = "org.hibernate.type.UUIDCharType")
    @Column(columnDefinition = "CHAR(36)")
    var token: UUID? = null

    @Column
    var username: String? = null

    @Column
    var expirationTimestamp: Long? =  null

}