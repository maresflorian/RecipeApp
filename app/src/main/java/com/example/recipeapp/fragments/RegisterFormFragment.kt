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

class RegisterFormFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register_form, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()

        val emailTextField = view.findViewById<EditText>(R.id.et_email)
        val passwordTextField = view.findViewById<EditText>(R.id.et_password)
        val registerButton = view.findViewById<Button>(R.id.btn_register)
        val loginButton = view.findViewById<Button>(R.id.btn_login)

        loginButton.setOnClickListener { goToLogin() }

        registerButton.setOnClickListener {
            val email = emailTextField.text.toString()
            val password = passwordTextField.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                registerUser(email, password)
            } else {
                showAlert("Please fill in all fields")
            }
        }
    }

    private fun goToLogin() {
        findNavController().navigate(R.id.action_registerFormFragment_to_loginFragment)
    }

    private fun registerUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    findNavController().navigate(R.id.action_registerFormFragment_to_recipesFragment)
                } else {
                    showAlert("Registration failed: ${task.exception?.message}")
                }
            }
    }

    private fun showAlert(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}
