package com.example.zapfunfoodorderingapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_forgot_password.*

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        auth = FirebaseAuth.getInstance()

        changepassbtn.setOnClickListener() {
            changepassword()
        }
    }

    private fun changepassword() {

        if(newpassword.text.isNotEmpty() &&
                confirmpassword.text.isNotEmpty()) {

            if(newpassword.text.toString().equals(confirmpassword.text.toString())) {
                val user = auth.currentUser
                if(user != null && user.email != null) {
                    val credential = EmailAuthProvider
                        .getCredential(user.email!!, newpassword.text.toString())

                    // Prompt the user to re-provide their sign-in credentials
                    user.reauthenticate(credential)
                        .addOnCompleteListener {
                            if(it.isSuccessful) {
                                Toast.makeText(this, "Re-authentication success", Toast.LENGTH_SHORT).show()
                                user!!.updatePassword(newpassword.text.toString())
                                    .addOnCompleteListener { task ->
                                        if (task.isSuccessful) {
                                            Toast.makeText(this, "Passowrd changed success", Toast.LENGTH_SHORT).show()
                                            auth.signOut()
                                            startActivity(Intent(this, LoginActivity:: class.java))
                                            finish()
                                        }
                                    }
                            } else {
                                Toast.makeText(this, "Re-authentication failed", Toast.LENGTH_SHORT).show()
                            }
                        }

                } else {
                    startActivity(Intent(this, LoginActivity:: class.java))
                    finish()
                }

            }else {
                Toast.makeText(this,"Password missmatching.", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this,"Please enter all the field.", Toast.LENGTH_SHORT).show()
        }
    }
}