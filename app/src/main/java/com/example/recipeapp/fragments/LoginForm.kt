package com.example.recipeapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.recipeapp.R

class LoginForm : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login_form, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val emailEditText = view.findViewById<EditText>(R.id.et_email)
        val passwordEditText = view.findViewById<EditText>(R.id.et_password)
        val loginButton = view.findViewById<Button>(R.id.btn_login)

        if (emailEditText == null) {
            Log.e("LoginForm", "emailEditText is null")
        }
        if (passwordEditText == null) {
            Log.e("LoginForm", "passwordEditText is null")
        }

        loginButton.setOnClickListener {
            val email = emailEditText?.text.toString()
            val password = passwordEditText?.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                findNavController().navigate(R.id.action_loginForm_to_recipesFragment)
            }
        }
    }

}
