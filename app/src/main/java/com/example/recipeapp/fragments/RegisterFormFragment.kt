package com.example.recipeapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.recipeapp.R
import com.google.firebase.auth.FirebaseAuth

// Fragment pentru formularul de înregistrare
class RegisterFormFragment : Fragment() {

    // FirebaseAuth pentru gestionarea înregistrării utilizatorilor
    private lateinit var auth: FirebaseAuth

    // Creează și returnează layout-ul pentru fragmentul de înregistrare
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register_form, container, false)
    }

    // După ce view-ul a fost creat, inițializează componentele UI și FirebaseAuth
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inițializează FirebaseAuth
        auth = FirebaseAuth.getInstance()

        // Referințe către elementele UI din layout: EditText-uri pentru email și parolă, butoane pentru înregistrare și login
        val emailTextField = view.findViewById<EditText>(R.id.et_email)
        val passwordTextField = view.findViewById<EditText>(R.id.et_password)
        val registerButton = view.findViewById<Button>(R.id.btn_register)
        val loginButton = view.findViewById<Button>(R.id.btn_login)

        // Setează un click listener pe butonul de login pentru a naviga la formularul de login
        loginButton.setOnClickListener { goToLogin() }

        // Setează un click listener pe butonul de înregistrare
        registerButton.setOnClickListener {
            // Obține textul din câmpurile de email și parolă
            val email = emailTextField.text.toString()
            val password = passwordTextField.text.toString()

            // Verifică dacă câmpurile nu sunt goale
            if (email.isNotEmpty() && password.isNotEmpty()) {
                // Apelează funcția de înregistrare a utilizatorului
                registerUser(email, password)
            } else {
                // Afișează un mesaj de alertă dacă câmpurile sunt goale
                showAlert("Please fill in all fields")
            }
        }
    }

    // Funcție pentru a naviga către fragmentul de login
    private fun goToLogin() {
        findNavController().navigate(R.id.action_registerFormFragment_to_loginFragment)
    }

    // Funcție pentru a înregistra utilizatorul folosind Firebase Authentication
    private fun registerUser(email: String, password: String) {
        // Apelează FirebaseAuth pentru a crea un cont de utilizator cu e-mail și parolă
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                // Dacă înregistrarea are succes, navighează la fragmentul cu rețete
                if (task.isSuccessful) {
                    findNavController().navigate(R.id.action_registerFormFragment_to_recipesFragment)
                } else {
                    // Dacă înregistrarea eșuează, afișează un mesaj de eroare
                    showAlert("Registration failed: ${task.exception?.message}")
                }
            }
    }

    // Funcție pentru a afișa un mesaj Toast cu un text dat
    private fun showAlert(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}
