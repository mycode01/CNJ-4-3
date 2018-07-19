package com.example.demo

import com.example.demo.dto.AccountNumber
import com.example.demo.dto.User
import com.example.demo.entity.Account
import com.example.demo.repository.AccountRepository
import com.example.demo.service.AccountService
import com.example.demo.service.UserService
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit4.SpringRunner
import java.util.*

@RunWith(SpringRunner::class) //4-3
class AccountServiceTests {

    @MockBean
    lateinit var userService: UserService

    @MockBean
    lateinit var accountRepository: AccountRepository

    lateinit var accountService: AccountService

    @Before
    fun before() {
        accountService = AccountService(accountRepository, userService) // autowired를 사용하지 않고, service를 생성해야하므로
    }

    @Test //단순한 서비스 단위 테스트라면 resources가 필요없음.
    fun getUserAccountsReturnsSingleAccount() {
        given(this.accountRepository.findAccountsByUsername("user"))
                .willReturn(
                        Collections.singletonList(
                                Account("user", AccountNumber("123456789"))
                        )
                )

        given(this.userService.getAuthenticatedUser())
                .willReturn(User(0L, "user", "john", "Doe"))

        var actual = this.accountService.getUserAccounts()

        assertThat(actual).size().isNotEqualTo(0)
        assertThat(actual[0].username).isEqualTo("user")
        assertThat(actual[0].accountNumber).isEqualTo(AccountNumber("123456789"))
    }
}
