package com.example.demo.dto

import com.fasterxml.jackson.annotation.JsonValue
import org.springframework.util.Assert

class AccountNumber(val accountNumber: String) {
    init {
        Assert.notNull(accountNumber, "Account Number must not be null")
        Assert.isTrue(accountNumber.length == 9,
                "Account Number must be exactly 9 characters")
    }

    operator fun invoke():String{
        return accountNumber
    } //

    override fun toString(): String {
        return accountNumber
    }

    override fun equals(o: Any?): Boolean {
        if (this === o)
            return true
        if (o !is AccountNumber)
            return false

        val that = o as AccountNumber?

        return if (accountNumber != null)
            accountNumber == that!!.accountNumber
        else
            that!!.accountNumber == null

    }

    override fun hashCode(): Int {
        return accountNumber?.hashCode() ?: 0
    }
}
