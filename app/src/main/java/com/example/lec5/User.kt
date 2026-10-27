package com.example.lec5

import java.io.Serializable

data class User(
    val fullName: String,
    val email: String,
    val username: String,
    val phone: String,
    val pass: String
) : Serializable
