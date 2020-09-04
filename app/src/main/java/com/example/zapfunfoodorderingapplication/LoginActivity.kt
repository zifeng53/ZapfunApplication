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

        //login button
        loginbtn.setOnClickListener {
            dologin()
        }

        //forgot password text button
        forgotpassword.setOnClickListener {
            startActivity(Intent(this, ForgotPasswordActivity::class.java))
        }
    }

    //login button function
    private fun dologin() {
        if(emailL.text.toString().isEmpty()) {
            emailL.error = "Please enter email"
            emailL.requestFocus()
            return
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(emailL.text.toString()).matches()) {
            emailL.error = "Please enter valid email"
            emailL.requestFocus()
            return
        }

        if(password.text.toString().isEmpty()) {
            password.error = "Please enter password"
            password.requestFocus()
            return
        }

        //user login with email and password
        auth.signInWithEmailAndPassword(emailL.text.toString(), password.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    updateUI(null)
                }
            }
    }

    //check if the user logged in or not
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(currentUser : FirebaseUser?) {

        //check the im not robot checkbox
        if(checkBox6.isChecked == true) {
            //if login successful go to this page
            if(currentUser != null) {
                if(currentUser.isEmailVerified == true) {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }

                else if(currentUser.isEmailVerified == false) {
                    Toast.makeText(this, "Please verify your email address", Toast.LENGTH_SHORT).show()
                }
            }

            else if (currentUser == null){
                // If login fails, display a message to the user.
                Toast.makeText(baseContext, "login failed.",
                    Toast.LENGTH_SHORT).show()
            }
        }

        else if(checkBox6.isChecked == false) {
            Toast.makeText(baseContext, "Please check i am not a robot.",
                Toast.LENGTH_SHORT).show()
        }

    }
}