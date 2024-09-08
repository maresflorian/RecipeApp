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

class RegisterFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("RegisterFragment", "onCreateView called")
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("RegisterFragment", "onViewCreated called")

        auth = FirebaseAuth.getInstance()
        Log.d("RegisterFragment", "FirebaseAuth initialized")

        val emailEditText = view.findViewById<EditText>(R.id.et_email)
        val passwordEditText = view.findViewById<EditText>(R.id.et_password)
        val registerButton = view.findViewById<Button>(R.id.btn_register)
        val loginButton = view.findViewById<Button>(R.id.btn_login)

        registerButton.setOnClickListener {
            Log.d("RegisterFragment", "Register button clicked")
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                Log.d("RegisterFragment", "Email and password are not empty. Starting registration.")
                registerUser(email, password)
            } else {
                Log.d("RegisterFragment", "Email or password is empty")
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }

        loginButton.setOnClickListener {
            Log.d("RegisterFragment", "Login button clicked. Navigating to loginFragment")
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
    }

    private fun registerUser(email: String, password: String) {
        Log.d("RegisterFragment", "Attempting to register user with email: $email")
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    Log.d("RegisterFragment", "Registration successful. Navigating to recipesFragment")
                    findNavController().navigate(R.id.action_registerFragment_to_recipesFragment)
                } else {
                    Log.e("RegisterFragment", "Registration failed: ${task.exception?.message}")
                    Toast.makeText(requireContext(), "Registration failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                }
            }
            .addOnFailureListener { e ->
                Log.e("RegisterFragment", "Registration failed due to: ${e.message}")
            }
    }
}
