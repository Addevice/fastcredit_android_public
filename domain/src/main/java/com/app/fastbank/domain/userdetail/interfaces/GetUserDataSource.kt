package com.app.fastbank.domain.userdetail.interfaces

import com.app.fastbank.domain.userdetail.models.User

interface GetUserDataSource {
    suspend fun getUser(): User?
}