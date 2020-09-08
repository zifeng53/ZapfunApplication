package com.example.zapfunfoodorderingapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_forgot_password.*
import kotlinx.android.synthetic.main.activity_register_account.*

class RegisterAccountActivity : AppCompatActivity() {

    //declare variable
    private lateinit var auth: FirebaseAuth
    lateinit var email: EditText
    lateinit var first_name: EditText
    lateinit var last_name: EditText
    lateinit var password: EditText
    lateinit var phoneno: EditText
    lateinit var floor: EditText
    lateinit var address: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_account)

        //set authentication to auth
        auth = FirebaseAuth.getInstance()
        email = findViewById(R.id.email_register)
        first_name = findViewById(R.id.fname)
        last_name = findViewById(R.id.lname)
        password = findViewById(R.id.password_register)
        phoneno = findViewById(R.id.phoneno)
        floor = findViewById(R.id.editTextFloor)
        address = findViewById(R.id.editTextAddress)

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
        val email = email.text.toString()
        val first_name = first_name.text.toString()
        val last_name = last_name.text.toString()
        val password = password.text.toString()
        val phoneno = phoneno.text.toString()
        val floor = floor.text.toString()
        val address = address.text.toString()

        if(email_register.text.toString().isEmpty()) {
            email_register.error = "Please enter email"
            email_register.requestFocus()
            return
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email_register.text.toString()).matches()) {
            email_register.error = "Please enter valid email"
            email_register.requestFocus()
            return
        }

        if(fname.text.toString().isEmpty()) {
            fname.error = "Please enter first name"
            fname.requestFocus()
            return
        }

        if(lname.text.toString().isEmpty()) {
            lname.error = "Please enter last name"
            lname.requestFocus()
            return
        }

        if(password_register.text.toString().isEmpty()) {
            password_register.error = "Please enter password"
            password_register.requestFocus()
            return
        }

        if(checkBox7.isChecked == true) {
            //create user in firebase
            auth.createUserWithEmailAndPassword(email_register.text.toString(), password_register.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        //send email verification
                        val user = auth.currentUser
                        user!!.sendEmailVerification()
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    val database = FirebaseDatabase.getInstance().getReference("User_Profile")
                                    val userid = database.push().key

                                    val Userdata = UserProfile(userid, email, first_name, last_name, password, phoneno, floor, address)
                                    if (userid != null) {
                                       database.child(userid).setValue(Userdata)
                                    }

                                    //if successful register go to login
                                    startActivity(Intent(this,LoginActivity::class.java))
                                    finish()
                                }
                            }
                    } else {
                        // If register fails, display a message to the user.
                        Toast.makeText(baseContext, "Register failed, please try again later.",
                            Toast.LENGTH_SHORT).show()
                    }

                }
        }

        else if(checkBox7.isChecked == false) {
            Toast.makeText(baseContext, "PLease check the terms and condition.",
                Toast.LENGTH_SHORT).show()
        }

    }
}