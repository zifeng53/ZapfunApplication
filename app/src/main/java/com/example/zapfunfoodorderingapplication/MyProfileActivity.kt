package com.example.zapfunfoodorderingapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_my_profile.*

class MyProfileActivity : AppCompatActivity() {

        private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_profile)

        mAuth = FirebaseAuth.getInstance()

        readData()
    }

    fun readData() {
        val user = mAuth.currentUser

        if (user != null) {
            FirebaseDatabase.getInstance().reference
                .child("User_Profile")
                .orderByChild(user.uid)
                .addValueEventListener(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {

                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        val map = p0.value as Map<*, *>
                        fname_view.text = map["first_name"].toString()
                        lname_view.text = map["last_name"].toString()
                        email_view.text = map["email"].toString()
                        phone_view.text = map["phoneno"].toString()
                    }
                })
        }
    }
}

