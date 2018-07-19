package com.example.demo

import com.example.demo.entity.Account
import com.example.demo.service.AccountService
import com.example.demo.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/v1"])
class AccountController(private val accountService: AccountService) {

    @RequestMapping("/accounts")
    fun getUserAccount() = accountService.getUserAccounts().or { throw Exception("Accounts for user do not exist") }.let { ResponseEntity<List<Account>>(it, HttpStatus.OK) }


}
