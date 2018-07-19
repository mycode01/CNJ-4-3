package com.example.demo.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.net.URI
import org.springframework.http.RequestEntity.get

import com.example.demo.dto.User


@Service
class UserService{
    @Autowired
    lateinit var restTemplate: RestTemplate
    @Value("\${user-service.host:user-service}")
    lateinit var serviceHost: String

    fun getAuthenticatedUser(): User? {
        var url: URI = URI.create("http://$serviceHost/uaa/v1/me")
        var request = get(url).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build()
        return restTemplate.exchange(request, User::class.java).body
    }

    @Bean
    fun restTemplate(builder: RestTemplateBuilder): RestTemplate {
        return builder.build()
    }

}
