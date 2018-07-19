package com.example.demo

import com.example.demo.service.UserService
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest
import org.springframework.core.io.ClassPathResource
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.client.MockRestServiceServer

import org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo
import org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess

@RunWith(SpringRunner::class)
@RestClientTest(UserService::class)
@AutoConfigureWebClient(registerRestTemplate = true)
class UserServiceTests {

    @Value("\${user-service.host:user-service}")
    lateinit var serviceHost: String

    @Autowired
    lateinit var userService: UserService
    @Autowired
    lateinit var server: MockRestServiceServer

    @Test
    fun getAuthenticatedUserShouldReturnUser() {
        this.server
                .expect(requestTo(
                        "http://$serviceHost/uaa/v1/me"))
                .andRespond(withSuccess(
                        this::class.java.classLoader.getResource("user.json").readText(), MediaType.APPLICATION_JSON
                ))

        val user = userService.getAuthenticatedUser()!! //nullable 문제때문에 !! 사용

        assertThat(user.username).isEqualTo("user")
        assertThat(user.firstName).isEqualTo("John")
        assertThat(user.lastName).isEqualTo("Doe")
        assertThat(user.createdAt).isEqualTo(12345)
        assertThat(user.lastModified).isEqualTo(12346)
        assertThat(user.id).isEqualTo(0L)
    }


}