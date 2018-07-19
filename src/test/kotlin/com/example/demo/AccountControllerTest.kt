package com.example.demo

import com.example.demo.entity.Account
import com.example.demo.service.AccountService
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.jackson.JsonObjectSerializer
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import java.util.*

@RunWith(SpringRunner::class)
@WebMvcTest(controllers=[AccountController::class], secure = false) // actuator때문인지 secure를 false처리하지 않으면 401이 발생함
class AccountControllerTest {
    @Autowired
    lateinit var mvc: MockMvc

    @MockBean
    lateinit var accountService: AccountService

    @Test
    fun getUserAccountsShouldReturnAccounts() {
        val content = "[{\"username\":\"user\", \"accountNumber\":\"123456789\"}]"

        given(this.accountService.getUserAccounts()).willReturn(
                Collections.singletonList(Account("user", "123456789")))

        this.mvc.perform(get("/v1/accounts").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk).andExpect(content().json(content))
    }

}