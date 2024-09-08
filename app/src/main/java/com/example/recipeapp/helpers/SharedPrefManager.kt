package com.example.recipeapp.helpers

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

class SharedPrefManager(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("token", token)
        editor.apply()
    }

    fun getToken(): String? {
        return sharedPreferences.getString("token", null)
    }

    // Metodă pentru a afișa token-ul stocat în Logcat
    fun logStoredToken() {
        val token = getToken()
        Log.d("SharedPrefManager", "Token stored: $token")
    }
}
