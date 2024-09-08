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

class LoginFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }


//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        Log.d("LoginFragment", "onViewCreated called")
//
//        // Inițializează Firebase Auth
//        auth = FirebaseAuth.getInstance()
//        Log.d("LoginFragment", "FirebaseAuth initialized")
//
//        // Inițializează elementele UI
//        val emailEditText = view.findViewById<EditText>(R.id.et_email)
//        val passwordEditText = view.findViewById<EditText>(R.id.et_password)
//        val loginButton = view.findViewById<Button>(R.id.btn_login)
//        val registerButton = view.findViewById<Button>(R.id.btn_register)
//
//        // Butonul de login
//        loginButton.setOnClickListener {
//            val email = emailEditText.text.toString()
//            val password = passwordEditText.text.toString()
//
//            Log.d("LoginFragment", "Login button clicked - email: $email")
//
//            if (email.isNotEmpty() && password.isNotEmpty()) {
//                loginUser(email, password)
//            } else {
//                Log.d("LoginFragment", "Email or password is empty")
//                Toast.makeText(requireContext(), "Please enter email and password", Toast.LENGTH_SHORT).show()
//            }
//        }
//
//        // Butonul de înregistrare
//        registerButton.setOnClickListener {
//            Log.d("LoginFragment", "Register button clicked")
//            findNavController().navigate(R.id.action_loginFragment_to_registerFormFragment)
//        }
//    }
override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val emailEditText = view.findViewById<EditText>(R.id.et_email)
    val passwordEditText = view.findViewById<EditText>(R.id.et_password)
    val loginButton = view.findViewById<Button>(R.id.btn_login)
    val registerButton = view.findViewById<Button>(R.id.btn_register)

    loginButton.setOnClickListener {
        val email = emailEditText?.text.toString()
        val password = passwordEditText?.text.toString()

        Log.d("LoginFragment", "Login button clicked - email: $email")

        if (email.isNotEmpty() && password.isNotEmpty()) {
            Log.d("LoginFragment", "Navigating to LoginFormFragment")
            // Aici navigăm către LoginFormFragment sau alt fragment
            findNavController().navigate(R.id.action_loginFragment_to_loginForm)
        } else {
            Log.d("LoginFragment", "Email or password is empty")
            Toast.makeText(requireContext(), "Please enter email and password", Toast.LENGTH_SHORT).show()
        }
    }
//     Butonul de înregistrare
        registerButton.setOnClickListener {
            Log.d("LoginFragment", "Register button clicked")
            findNavController().navigate(R.id.action_loginFragment_to_registerFormFragment)
        }
}



    private fun loginUser(email: String, password: String) {
        Log.d("LoginFragment", "Attempting to log in user with email: $email")

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    Log.d("LoginFragment", "Login successful")
                    findNavController().navigate(R.id.action_loginFragment_to_recipesFragment)
                } else {
                    Log.e("LoginFragment", "Login failed: ${task.exception?.message}")
                    Toast.makeText(requireContext(), "Authentication failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                }
            }
            .addOnFailureListener { e ->
                Log.e("LoginFragment", "Login failed due to: ${e.message}")
            }
    }
}
