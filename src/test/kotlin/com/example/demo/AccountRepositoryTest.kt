package com.example.demo

import com.example.demo.dto.AccountNumber
import com.example.demo.entity.Account
import com.example.demo.repository.AccountRepository
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.junit4.SpringRunner
import org.assertj.core.api.Assertions.assertThat


@RunWith(SpringRunner::class)
@DataJpaTest
class AccountRepositoryTest {
    companion object {
        private val ACCOUNT_NUMBER = AccountNumber("098765432")
    }

    @Autowired
    lateinit var accountRepository: AccountRepository
    @Autowired
    lateinit var entityManager: TestEntityManager

    @Test
    fun findUserAccountsShouldReturnAccounts() {
        this.entityManager.persist(Account("jack", ACCOUNT_NUMBER))
        val account = this.accountRepository.findAccountsByUsername("jack")
        assertThat(account).size().isNotEqualTo(0)
        val actual = account[0]
        assertThat(actual.accountNumber).isEqualTo(ACCOUNT_NUMBER.accountNumber) // 객체비교가 아니라 String으로 교체됐으므로..
        assertThat(actual.username).isEqualTo("jack")
    }

    @Test
    fun findAccountShouldReturnAccount(){
        this.entityManager.persist(Account("jill", ACCOUNT_NUMBER))
        val account = this.accountRepository.findAccountByAccountNumber(ACCOUNT_NUMBER())
        assertThat(account).isNotNull
        assertThat(account?.accountNumber).isEqualTo(ACCOUNT_NUMBER()) // 객체비교시 에러발생함, invoke 구현으로 최대한 비슷하게.. 우회함
    } // kotlin은 nullable타입이 따로 존재하므로 어쩔수 없이 ?를 사용함, 바로 밑 테스트 때문에

    @Test
    fun findAccountShouldReturnNull(){
        this.entityManager.persist(Account("jack", ACCOUNT_NUMBER))
        val account = this.accountRepository.findAccountByAccountNumber(AccountNumber("000000000")()) // AccountNumber.accountNumber 구문과 동일
        assertThat(account).isNull()
    }

}