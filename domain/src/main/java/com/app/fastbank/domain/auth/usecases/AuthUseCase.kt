package com.app.fastbank.domain.auth.usecases

import com.app.fastbank.domain.auth.interfaces.AuthDataSource

class AuthUseCase(
    private val authDataSource: AuthDataSource
) {
    suspend operator fun invoke(email: String, password: String){
        authDataSource.auth(email, password)
    }
}