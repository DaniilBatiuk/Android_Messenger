package com.example.lab3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged

class RegisterActivity : AppCompatActivity() {
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var confirmPasswordEditText: EditText
    private lateinit var errorTextView: TextView
    private lateinit var db: DB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        emailEditText = findViewById(R.id.editTextTextEmailAddress3)
        passwordEditText = findViewById(R.id.editTextTextPassword2)
        confirmPasswordEditText = findViewById(R.id.editTextTextPassword4)
        errorTextView = findViewById(R.id.textView4)
        db = DB(this)
    }

    fun login(view: View) {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun register(view: View) {
        val email: String = emailEditText.text.toString()
        val password: String = passwordEditText.text.toString()
        val confirmPassword: String = confirmPasswordEditText.text.toString()

        if (db.emailExists(email)) {
            Toast.makeText(this, "This email already exist", Toast.LENGTH_SHORT).show()
            return
        }

        try {
            db.addUser("username", email, password)
            Toast.makeText(this, "success", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        } catch (e: Exception) {
            Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
        }

        if (password == confirmPassword) {
            val logMessage = "Email: $email, Password: $password"
            println(logMessage)
            errorTextView.text = ""
        } else {
            errorTextView.text = "Passwords do not match"
        }
    }

}