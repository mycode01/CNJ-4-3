package com.example.demo.entity

import javax.persistence.*

@Entity
class CreditCard : BaseEntity {

    @get:Id
    @get:GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    var number: String? = null

    @Enumerated(EnumType.STRING)
    var type: CreditCardType? = null

    constructor() {}

    constructor(number: String, type: CreditCardType) {
        this.number = number
        this.type = type
    }

    override fun toString(): String {
        return ("CreditCard{" + "id=" + id + ", number='"
                + number!!.replace("([\\d]{4})(?!$)".toRegex(), "****-") + '\''.toString() + ", type='" + type
                + '\''.toString() + '}'.toString())
    }
}

enum class CreditCardType {
    VISA, MASTERCARD, AMERICAN_EXPRESS
}
