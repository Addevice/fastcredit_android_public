package com.app.fastbank.data

import android.content.SharedPreferences
import com.app.fastbank.domain.userdetail.interfaces.GetUserDataSource
import com.app.fastbank.domain.userdetail.models.User


class UserDataSource(private val preferences: SharedPreferences): GetUserDataSource  {

    override suspend fun getUser(): User? {
        val accessToken = preferences.getString(Constants.ACCESS_TOKEN, "")
        val response = RetrofitService.get().getUser(accessToken).execute()
        if (response.isSuccessful) {
            return response.body()
        } else {
            throw Exception(response.message())
        }
    }
}