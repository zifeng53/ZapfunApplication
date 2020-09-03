package com.example.zapfunfoodorderingapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register_account.*
import kotlinx.android.synthetic.main.activity_login.email as email1
import kotlinx.android.synthetic.main.activity_register_account.password as password1


class LoginActivity : AppCompatActivity() {

    //global variable
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //assign authentication to auth
        auth = FirebaseAuth.getInstance()

        //dont have account text to register page
        noaccount.setOnClickListener {
            startActivity(Intent(this, RegisterAccountActivity::class.java))
            finish()
        }
    }

    //check if the user logged in or not
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    fun updateUI(currentUser : FirebaseUser?) {

    }
}