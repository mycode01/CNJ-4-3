package com.example.demo.dto

import java.io.Serializable

class User(id: Long?, username: String, firstName: String, lastName: String) : Serializable {
    var id = id
    var username = username
    var firstName = firstName
    var lastName = lastName
    var createdAt: Long? = null
    var lastModified: Long? = null
}