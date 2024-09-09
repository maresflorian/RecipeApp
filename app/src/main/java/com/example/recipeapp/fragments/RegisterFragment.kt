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
import com.example.recipeapp.R
import com.google.firebase.auth.FirebaseAuth

// Fragment pentru formularul de înregistrare
class RegisterFragment : Fragment() {

    // Declara FirebaseAuth pentru gestionarea înregistrării utilizatorilor
    private lateinit var auth: FirebaseAuth

    // Creează și returnează layout-ul pentru acest fragment
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("RegisterFragment", "onCreateView called")  // Log pentru urmărirea ciclului de viață al fragmentului
        return inflater.inflate(R.layout.fragment_register, container, false)  // Inflează layout-ul din XML
    }

    // Metodă apelată după ce view-ul a fost creat, pentru inițializarea logicii UI
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("RegisterFragment", "onViewCreated called")  // Log pentru urmărirea ciclului de viață al fragmentului

        // Inițializează FirebaseAuth
        auth = FirebaseAuth.getInstance()
        Log.d("RegisterFragment", "FirebaseAuth initialized")

        // Referințe către elementele UI (EditText pentru email și parolă și butoane de înregistrare și login)
        val emailEditText = view.findViewById<EditText>(R.id.et_email)
        val passwordEditText = view.findViewById<EditText>(R.id.et_password)
        val registerButton = view.findViewById<Button>(R.id.btn_register)
        val loginButton = view.findViewById<Button>(R.id.btn_login)

        // Setează un click listener pe butonul de înregistrare
        registerButton.setOnClickListener {
            Log.d("RegisterFragment", "Register button clicked")
            // Obține textul din câmpurile de email și parolă
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Verifică dacă câmpurile nu sunt goale
            if (email.isNotEmpty() && password.isNotEmpty()) {
                Log.d("RegisterFragment", "Email and password are not empty. Starting registration.")
                // Apelează funcția de înregistrare a utilizatorului
                registerUser(email, password)
            } else {
                Log.d("RegisterFragment", "Email or password is empty")  // Log pentru cazul când unul din câmpuri este gol
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()  // Afișează un mesaj Toast
            }
        }

        // Setează un click listener pe butonul de login pentru a naviga către fragmentul de login
        loginButton.setOnClickListener {
            Log.d("RegisterFragment", "Login button clicked. Navigating to loginFragment")
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)  // Navigare la formularul de login
        }
    }

    // Funcție pentru a înregistra utilizatorul folosind Firebase Authentication
    private fun registerUser(email: String, password: String) {
        Log.d("RegisterFragment", "Attempting to register user with email: $email")
        auth.createUserWithEmailAndPassword(email, password)  // Crează contul utilizatorului
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    Log.d("RegisterFragment", "Registration successful. Navigating to recipesFragment")
                    findNavController().navigate(R.id.action_registerFragment_to_recipesFragment)  // Dacă înregistrarea reușește, navighează la rețete
                } else {
                    Log.e("RegisterFragment", "Registration failed: ${task.exception?.message}")
                    Toast.makeText(requireContext(), "Registration failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()  // Afișează un mesaj de eroare
                }
            }
            .addOnFailureListener { e ->
                Log.e("RegisterFragment", "Registration failed due to: ${e.message}")  // Loghează eroarea
            }
    }
}
