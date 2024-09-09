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

// Fragment pentru formularul de login
class LoginForm : Fragment() {

    // Creează și returnează layout-ul pentru fragmentul de login
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflează layout-ul specificat în fragment_login_form.xml
        return inflater.inflate(R.layout.fragment_login_form, container, false)
    }

    // Metodă apelată după ce view-ul a fost creat; folosită pentru a inițializa componentele UI
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Referințe către EditText-urile din layout pentru e-mail și parolă
        val emailEditText = view.findViewById<EditText>(R.id.et_email)
        val passwordEditText = view.findViewById<EditText>(R.id.et_password)
        // Referință către butonul de login
        val loginButton = view.findViewById<Button>(R.id.btn_login)

        // Verifică dacă emailEditText este null și înregistrează un mesaj de eroare în log (în mod normal nu ar trebui să fie null)
        if (emailEditText == null) {
            Log.e("LoginForm", "emailEditText is null")
        }
        // Verifică dacă passwordEditText este null și înregistrează un mesaj de eroare în log
        if (passwordEditText == null) {
            Log.e("LoginForm", "passwordEditText is null")
        }

        // Setează un click listener pe butonul de login
        loginButton.setOnClickListener {
            // Obține textul din câmpurile de e-mail și parolă
            val email = emailEditText?.text.toString()
            val password = passwordEditText?.text.toString()

            // Verifică dacă atât e-mailul, cât și parola sunt completate
            if (email.isNotEmpty() && password.isNotEmpty()) {
                // Navighează la fragmentul care afișează rețetele, folosind NavController
                findNavController().navigate(R.id.action_loginForm_to_recipesFragment)
            }
        }
    }
}
