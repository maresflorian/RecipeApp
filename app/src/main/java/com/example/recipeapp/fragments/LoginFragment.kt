package com.example.recipeapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.example.recipeapp.R

// Fragment pentru autentificarea utilizatorilor
class LoginFragment : Fragment() {

    // Declara FirebaseAuth pentru gestionarea autentificării utilizatorilor
    private lateinit var auth: FirebaseAuth

    // Creează și returnează layout-ul pentru fragmentul de autentificare
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflează layout-ul pentru fragment_login.xml
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    // Metodă apelată după ce view-ul a fost creat; folosită pentru a inițializa componentele UI
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inițializează elementele UI din layout: EditText pentru email și parolă și butoanele de login și înregistrare
        val emailEditText = view.findViewById<EditText>(R.id.et_email)
        val passwordEditText = view.findViewById<EditText>(R.id.et_password)
        val loginButton = view.findViewById<Button>(R.id.btn_login)
        val registerButton = view.findViewById<Button>(R.id.btn_register)

        // Setează un click listener pe butonul de login
        loginButton.setOnClickListener {
            // Obține textul introdus în câmpurile de e-mail și parolă
            val email = emailEditText?.text.toString()
            val password = passwordEditText?.text.toString()

            // Înregistrează în log evenimentul de click pe butonul de login și adresa de e-mail introdusă
            Log.d("LoginFragment", "Login button clicked - email: $email")

            // Verifică dacă e-mailul și parola nu sunt goale
            if (email.isNotEmpty() && password.isNotEmpty()) {
                // Înregistrează în log și navighează la LoginFormFragment sau un alt fragment
                Log.d("LoginFragment", "Navigating to LoginFormFragment")
                findNavController().navigate(R.id.action_loginFragment_to_loginForm)
            } else {
                // Afișează un mesaj Toast și înregistrează în log că datele sunt incomplete
                Log.d("LoginFragment", "Email or password is empty")
                Toast.makeText(requireContext(), "Please enter email and password", Toast.LENGTH_SHORT).show()
            }
        }

        // Setează un click listener pe butonul de înregistrare
        registerButton.setOnClickListener {
            // Înregistrează în log evenimentul de click pe butonul de înregistrare și navighează la formularul de înregistrare
            Log.d("LoginFragment", "Register button clicked")
            findNavController().navigate(R.id.action_loginFragment_to_registerFormFragment)
        }
    }

    // Funcție pentru autentificarea utilizatorului folosind Firebase Authentication
    private fun loginUser(email: String, password: String) {
        // Înregistrează în log încercarea de autentificare
        Log.d("LoginFragment", "Attempting to log in user with email: $email")

        // Utilizează FirebaseAuth pentru a autentifica utilizatorul
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                // Dacă autentificarea reușește, navighează la fragmentul cu rețete
                if (task.isSuccessful) {
                    Log.d("LoginFragment", "Login successful")
                    findNavController().navigate(R.id.action_loginFragment_to_recipesFragment)
                } else {
                    // În caz de eșec, afișează un mesaj Toast și loghează eroarea
                    Log.e("LoginFragment", "Login failed: ${task.exception?.message}")
                    Toast.makeText(requireContext(), "Authentication failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                }
            }
            .addOnFailureListener { e ->
                // Înregistrează în log eroarea în caz de eșec
                Log.e("LoginFragment", "Login failed due to: ${e.message}")
            }
    }
}
