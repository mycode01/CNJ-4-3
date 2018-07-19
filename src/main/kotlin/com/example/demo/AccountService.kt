package com.example.demo.service

import com.example.demo.dto.User
import com.example.demo.entity.Account
import com.example.demo.repository.AccountRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*


@Service
class AccountService @Autowired constructor(private val accountRepository: AccountRepository, private val userService: UserService) { // 명시적으로만 써줌, 실제로는 @autowired니 constructor니 뒤의것도 필요없음
//
//    lateinit var accountRepository: AccountRepository
//    lateinit var userService: UserService
//
//    init {
//        this.accountRepository = ar
//        this.userService = us
//    }

    //mock 객체를 삽입할수있도록 @Autowired를 사용하지 않고 생성자에 넣어 초기화 할수 있도록해줌


    fun getUserAccounts() = Optional.ofNullable(userService.getAuthenticatedUser())
            .map {
                accountRepository.findAccountsByUsername(it.username) }
            .orElse(listOf())
}