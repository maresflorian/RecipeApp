package com.example.recipeapp.utils.extensions

import android.util.Log

// Creare extensie pentru clasa String care adaugă funcționalitatea de a loga mesajele de eroare
fun String.logErrorMessage(tag: String = "AppTag") {
    // 'this' se referă la instanța String care apelează extensia
    Log.e(tag, this)
}
