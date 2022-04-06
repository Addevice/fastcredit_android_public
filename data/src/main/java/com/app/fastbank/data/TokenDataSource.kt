package com.app.fastbank.data

import android.content.SharedPreferences

class TokenDataSource(private val preferences: SharedPreferences)  : RetrofitDataSource() {

    override suspend fun auth(email: String, password: String): String {
       val token = super.auth(email, password)
        val editor = preferences.edit()
        editor.putString(Constants.ACCESS_TOKEN, token)
        editor.apply()
        return token
    }

}