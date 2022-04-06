package com.app.fastbank.domain.userdetail.models

data class User (
    var name: String = "",
    var surname: String = "",
    var email: String = "",
    var password: String = "",
    var avatarUrl: String? = null,
    var company: String? = null,
    var location: String? = null
)