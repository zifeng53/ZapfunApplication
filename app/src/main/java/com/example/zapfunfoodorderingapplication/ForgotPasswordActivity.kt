package com.example.zapfunfoodorderingapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
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

        if(email.text.isNotEmpty() &&
                newpassword.text.isNotEmpty() &&
                confirmpassword.text.isNotEmpty()) {

            if(newpassword.text.toString().equals(confirmpassword.text.toString())) {

                val user = auth.currentUser


                } else {
                Toast.makeText(this, "Password missmatching.", Toast.LENGTH_SHORT).show()
            }

        } else {
            Toast.makeText(this, " Please enter all the fields.",Toast.LENGTH_SHORT).show()
        }
    }

}