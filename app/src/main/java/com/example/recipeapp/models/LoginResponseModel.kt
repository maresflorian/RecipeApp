package com.example.recipeapp.models

data class LoginResponseModel(
    var token: String? = null,
    var invalidCredentials: Boolean = false
)
