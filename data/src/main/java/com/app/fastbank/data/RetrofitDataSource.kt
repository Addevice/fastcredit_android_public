package com.app.fastbank.data

import com.app.fastbank.domain.auth.interfaces.AuthDataSource

open class RetrofitDataSource : AuthDataSource {

    override suspend fun auth(email: String, password: String): String {
        val response = RetrofitService.get().login(email, password).execute()
        if (response.isSuccessful) {
            return response.body().toString()
        } else {
            throw Exception(response.message())
        }
    }
}