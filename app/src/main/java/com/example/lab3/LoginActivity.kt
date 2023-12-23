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
    var sPref: SharedPreferences? = null
    val LOGIN = "login"
    var PASSWORD = "password"
    private lateinit var db: DB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        errorTextView = findViewById(R.id.textView)
        db = DB(this)
    }


    fun login(view: View) {

        val emailEditText: EditText = findViewById(R.id.editTextTextEmailAddress)
        val passwordEditText: EditText = findViewById(R.id.editTextTextPassword)


        val email: String = emailEditText.text.toString()
        val password: String = passwordEditText.text.toString()

        if (db.validate(email, password)) {
            sPref = getPreferences(MODE_PRIVATE)
            val ed: SharedPreferences.Editor? = sPref?.edit()
            ed?.putString(LOGIN, email)
            ed?.putString(PASSWORD, password)
            ed?.apply()
            Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, MessengerActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, "Error with email or password", Toast.LENGTH_SHORT).show()
        }

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



    fun saveLogin(email:String,password:String) {
        sPref = getPreferences(MODE_PRIVATE)
        val ed: SharedPreferences.Editor = sPref!!.edit()
        ed.putString(LOGIN, email)
        ed.putString(PASSWORD, password)
        ed.commit()
        Toast.makeText(this, "Text saved", Toast.LENGTH_SHORT).show()
    }

}