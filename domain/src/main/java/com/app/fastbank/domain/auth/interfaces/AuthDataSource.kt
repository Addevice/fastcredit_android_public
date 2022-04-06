package com.app.fastbank.domain.auth.interfaces


interface AuthDataSource {

    suspend fun auth(email: String, password: String): String

}