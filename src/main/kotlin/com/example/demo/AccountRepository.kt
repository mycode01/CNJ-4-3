package com.example.demo.repository

import com.example.demo.dto.AccountNumber
import com.example.demo.entity.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository : JpaRepository<Account, Long> {
    fun findAccountsByUsername(@Param("username") username: String):List<Account>
    fun findAccountByAccountNumber(@Param("accountNumber") accountNumber: String): Account? // accountnumber타입변경됐으므로
}