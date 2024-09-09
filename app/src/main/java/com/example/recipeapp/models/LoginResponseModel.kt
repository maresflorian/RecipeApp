package com.example.recipeapp.models

data class LoginResponseModel(
    var token: String? = null, // Reprezintă token-ul JWT sau alt tip de token primit de la server după autentificare
    var invalidCredentials: Boolean = false // Indică dacă datele de autentificare (e-mail și parolă) sunt incorecte
)
