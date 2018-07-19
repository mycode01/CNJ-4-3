package com.example.demo.entity

import com.example.demo.dto.AccountNumber
import com.example.demo.or
import org.hibernate.annotations.TypeDef
import org.hibernate.annotations.TypeDefs
import org.springframework.util.Assert
import java.util.HashSet
import javax.persistence.*
import kotlin.properties.Delegates

@Entity
class Account() : BaseEntity() {

    @get:Id
    @get:GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    var username: String? = null

    //    @Column(columnDefinition="VARCHAR(255)")
//    @ElementCollection(targetClass = String::class)
//    var accountNumber: AccountNumber? = null
//    var accountNumber: String? = null // 하이버네이트에서 해당 타입을 찾을수 없어서 걍 string처리함
    var accountNumber: String by Delegates.vetoable("") { p, o, n -> // 굳이 null, length체크를 해야한다면
        n?.length == 9
//        Assert.notNull(n, "Account Number must not be null")
//        Assert.isTrue(accountNumber.length == 9,
//                "Account Number must be exactly 9 characters")
    }

    var defaultAccount: Boolean? = null

    @get:OneToMany(cascade = arrayOf(CascadeType.ALL), fetch = FetchType.EAGER)
    var creditCards: Set<CreditCard>? = null

    @get:OneToMany(cascade = arrayOf(CascadeType.ALL), fetch = FetchType.EAGER)
    var addresses: Set<Address>? = null

    init {
        this.creditCards = HashSet<CreditCard>()
        this.addresses = HashSet<Address>()
        this.defaultAccount = false
    }

    constructor(username: String, accountNumber: AccountNumber) : this() {
        this.username = username
        this.accountNumber = accountNumber.accountNumber
        this.defaultAccount = false
    }

    constructor(username: String, accountNumber: String) : this() {
        this.username = username
        this.accountNumber = accountNumber
        this.defaultAccount = false
    }

    override fun toString(): String {
        return ("Account{" + "id=" + id + ", username='" + username + '\''.toString()
                + ", accountNumber=" + accountNumber + ", defaultAccount=" + defaultAccount
                + ", creditCards=" + creditCards + ", addresses=" + addresses + "} "
                + super.toString())
    }
}
