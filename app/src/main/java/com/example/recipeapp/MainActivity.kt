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
        button.setBackgroundColor(ContextCompat.getColor(this, R.color.black))
        button.setOnClickListener {
            goToSecondActivity()
        }

    }


    private fun goToSecondActivity() {
        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
        finish()
    }
    override fun onStart() {
        super.onStart()
        "onStartMainActivity".logErrorMessage()

        // Inițializăm SharedPrefManager și afișăm token-ul stocat
        val sharedPrefManager = SharedPrefManager(this)
        sharedPrefManager.logStoredToken() // Afișează token-ul în Logcat
    }

    override fun onResume() {
        super.onResume()
        "onResumeMainActivity".logErrorMessage()
    }

    override fun onPause() {
        super.onPause()
        "onPauseMainActivity".logErrorMessage()
    }

    override fun onStop() {
        super.onStop()
        "onStopMainActivity".logErrorMessage()
    }

    override fun onDestroy() {
        super.onDestroy()
        "onDestroyMainActivity".logErrorMessage()
    }
}
