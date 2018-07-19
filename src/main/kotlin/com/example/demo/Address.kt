package com.example.demo.entity

import javax.persistence.*

@Entity
class Address : BaseEntity {

    @get:Id
    @get:GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    var street1: String? = null

    var street2: String? = null

    var state: String? = null

    var city: String? = null

    var country: String? = null

    var zipCode: Int? = null

    @Enumerated(EnumType.STRING)
    var addressType: AddressType? = null

    constructor() {}

    constructor(street1: String, street2: String, state: String, city: String,
                country: String, addressType: AddressType, zipCode: Int?) {
        this.street1 = street1
        this.street2 = street2
        this.state = state
        this.city = city
        this.country = country
        this.addressType = addressType
        this.zipCode = zipCode
    }

    override fun toString(): String {
        return ("Address{" + "id=" + id + ", street1='" + street1 + '\''.toString()
                + ", street2='" + street2 + '\''.toString() + ", state='" + state + '\''.toString() + ", city='"
                + city + '\''.toString() + ", country='" + country + '\''.toString() + ", addressType='"
                + addressType + '\''.toString() + ", zipCode=" + zipCode + '}'.toString())
    }
}

enum class AddressType {
    SHIPPING, BILLING
}

