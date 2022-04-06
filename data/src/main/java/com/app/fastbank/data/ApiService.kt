package com.app.fastbank.data

import com.app.fastbank.domain.userdetail.models.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {

    @GET("login")
    fun login(username: String, password: String): Call<String>

    @GET("user")
    fun getUser(@Header("Authorization") accessToken: String?): Call<User>
}