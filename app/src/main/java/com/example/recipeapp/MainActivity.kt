package com.example.recipeapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.recipeapp.helpers.SharedPrefManager
import com.example.recipeapp.utils.extensions.logErrorMessage

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Verificăm butonul
        val button = findViewById<Button>(R.id.btn_press)
        // Setăm culoarea de fundal a butonului la negru
        button.setBackgroundColor(ContextCompat.getColor(this, R.color.black))
        button.setOnClickListener {
            // Când se face click pe buton, se navighează la a doua activitate
            goToSecondActivity()
        }
    }

    // Funcție privată pentru a naviga la a doua activitate
    private fun goToSecondActivity() {
        val intent = Intent(this, SecondActivity::class.java) // Crează un intent pentru a lansa SecondActivity
        startActivity(intent) // Lansează activitatea
        finish() // Termină activitatea curentă
    }

    // Metodă apelată când activitatea este vizibilă și gata de a fi folosită
    override fun onStart() {
        super.onStart()
        "onStartMainActivity".logErrorMessage() // Loghează mesajul în logcat

        // Inițializăm SharedPrefManager și afișăm token-ul stocat
        val sharedPrefManager = SharedPrefManager(this)
        sharedPrefManager.logStoredToken() // Metodă care loghează token-ul salvat
    }

    // Metodă apelată când activitatea intră în prim plan
    override fun onResume() {
        super.onResume()
        "onResumeMainActivity".logErrorMessage() // Loghează mesajul în logcat
    }

    // Metodă apelată când activitatea intră în fundal, dar este încă vizibilă
    override fun onPause() {
        super.onPause()
        "onPauseMainActivity".logErrorMessage() // Loghează mesajul în logcat
    }

    // Metodă apelată când activitatea nu mai este vizibilă
    override fun onStop() {
        super.onStop()
        "onStopMainActivity".logErrorMessage() // Loghează mesajul în logcat
    }

    // Metodă apelată înainte de a distruge activitatea
    override fun onDestroy() {
        super.onDestroy()
        "onDestroyMainActivity".logErrorMessage() // Loghează mesajul în logcat
    }
}
