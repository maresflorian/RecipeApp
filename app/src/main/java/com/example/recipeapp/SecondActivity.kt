package com.example.recipeapp

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.recipeapp.R
import com.example.recipeapp.utils.extensions.logErrorMessage

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)  // Conectăm layout-ul corect
//        val layout = findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.main)
//        layout.setBackgroundColor(Color.parseColor("#D3D3D3"))
    }
    override fun onDestroy() {
        super.onDestroy()
        "onDestroySecondActivity".logErrorMessage()
    }

}
