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


class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()

        noaccount.setOnClickListener {
            startActivity(Intent(this, RegisterAccountActivity::class.java))
            finish()
        }

        loginbtn.setOnClickListener() {
            //startActivity(Intent(this, MainActivity::class.java))
            doLogin()
        }

        forgotpassword.setOnClickListener() {
            startActivity(Intent(this, ForgotPasswordActivity::class.java))
        }
    }

    private fun doLogin() {
        if(emaillogin.text.toString().isEmpty()) {
            emaillogin.error = "Please enter email"
            emaillogin.requestFocus()
            return
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(emaillogin.text.toString()).matches()) {
            emaillogin.error = "Please enter valid email"
            emaillogin.requestFocus()
            return
        }

        if(passwordlogin.text.toString().isEmpty()) {
            passwordlogin.error = "Please enter password"
            passwordlogin.requestFocus()
            return
        }

        auth.signInWithEmailAndPassword(emaillogin.text.toString(), passwordlogin.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    updateUI(null)
                }
            }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(currentUser: FirebaseUser?) {

        if(currentUser != null) {
            if(currentUser.isEmailVerified) {

                //login successful page go to
                startActivity(Intent(this, ForgotPasswordActivity::class.java))
                finish()
            } else {
                Toast.makeText(
                    baseContext, "please verify email address.",
                    Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(
                baseContext, "login failed.",
                Toast.LENGTH_SHORT).show()
        }
    }
}