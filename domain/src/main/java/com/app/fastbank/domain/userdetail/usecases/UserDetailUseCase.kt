package com.app.fastbank.domain.userdetail.usecases

import com.app.fastbank.domain.userdetail.interfaces.GetUserDataSource
import com.app.fastbank.domain.userdetail.models.User

class UserDetailUseCase(
    private val getUserDataSource: GetUserDataSource
) {

    suspend operator fun invoke(): User? {
       return getUserDataSource.getUser()
    }
}