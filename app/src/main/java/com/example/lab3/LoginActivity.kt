package com.example.lab3

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class LoginActivity : AppCompatActivity() {

    private lateinit var errorTextView: TextView
    SharedPreferences sPref = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        errorTextView = findViewById(R.id.textView)
    }


    fun login(view: View) {

        val emailEditText: EditText = findViewById(R.id.editTextTextEmailAddress)
        val passwordEditText: EditText = findViewById(R.id.editTextTextPassword)


        val email: String = emailEditText.text.toString()
        val password: String = passwordEditText.text.toString()


        Log.i("LoginActivity", "Email: $email, Password: $password")

        val intent = Intent(this, Messanger::class.java)
        startActivity(intent)
        saveLogin(email,password)
        finish()
    }

    fun register(view: View)
    {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
        finish()
    }

    val LOGIN = "login"
    val PASWORD = "password"

    fun saveLogin(email:String,password:String) {
        sPref = getPreferences(MODE_PRIVATE)
        val ed: SharedPreferences.Editor = sPref.edit()
        ed.putString(LOGIN, email)
        ed.putString(PASWORD, password)
        ed.commit()
        Toast.makeText(this, "Text saved", Toast.LENGTH_SHORT).show()
    }

}