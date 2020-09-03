package com.example.zapfunfoodorderingapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_forgot_password.*
import kotlinx.android.synthetic.main.activity_register_account.*
class RegisterAccountActivity : AppCompatActivity() {

    //global variable
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_account)

        //set authentication to auth
        auth = FirebaseAuth.getInstance()

        //register button
        registerbtn.setOnClickListener {
            registerUser()
        }

        //have an account to login page
        gotaccount.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        //right top back icon
        backbtn.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    //register function
    private fun registerUser() {
        if(email.text.toString().isEmpty()) {
            email.error = "Please enter email"
            email.requestFocus()
            return
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()) {
            email.error = "Please enter valid email"
            email.requestFocus()
            return
        }

        if(password.text.toString().isEmpty()) {
            password.error = "Please enter password"
            password.requestFocus()
            return
        }

        //create user in firebase
        auth.createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    //if successful register go to login
                    startActivity(Intent(this,LoginActivity::class.java))
                    finish()
                } else {
                    // If register fails, display a message to the user.
                    Toast.makeText(baseContext, "Register failed.",
                        Toast.LENGTH_SHORT).show()
                }

            }
    }
}