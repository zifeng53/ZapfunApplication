package com.example.zapfunfoodorderingapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_forgot_password.*


class ForgotPasswordActivity : AppCompatActivity() {

    //global variable
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        //firebase authentication to auth
        auth = FirebaseAuth.getInstance()

        //change password button
        changepassbtn.setOnClickListener {
            changepassword()
        }

        //top right back icon button
        backbtnforgot.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    //change password function
    private fun changepassword() {

        if(email_forgotpassword.text.toString().isNotEmpty() &&
                editTextPhone_forgotpassword.text.toString().isNotEmpty() &&
                newpassword_forgotpassword.text.toString().isNotEmpty() &&
                confirmpassword_forgotpassword.text.toString().isNotEmpty()) {

            if(newpassword_forgotpassword.text.toString().equals(confirmpassword_forgotpassword.text.toString())) {

                val user = auth.currentUser

                if (user != null) {
                    if(user.email != null && user.email == email_forgotpassword.text.toString()) {
                        val credential = EmailAuthProvider
                            .getCredential(user.email!!, "password1234")

                        user.reauthenticate(credential)
                            .addOnCompleteListener {
                                if(it.isSuccessful) {
                                    Toast.makeText(this, "reauthentication success", Toast.LENGTH_SHORT).show()

                                    user!!.updatePassword(newpassword_forgotpassword.text.toString())
                                        .addOnCompleteListener { task ->
                                            if (task.isSuccessful) {
                                                Toast.makeText(this, "password change success", Toast.LENGTH_SHORT).show()
                                                startActivity(Intent(this, LoginActivity::class.java))
                                            }
                                        }
                                }

                                else if(!it.isSuccessful) {
                                    Toast.makeText(this, "reauthentication failed", Toast.LENGTH_SHORT).show()
                                }
                            }
                    }

                    else if (user.email != email_forgotpassword.text.toString()){
                        email_forgotpassword.error = "Incorrect email"
                    }
                }

            } else {
                Toast.makeText(this, "Password missmatching", Toast.LENGTH_SHORT).show()
            }

        } else {
            Toast.makeText(this, " Please enter all the fields",Toast.LENGTH_SHORT).show()
        }
    }

}