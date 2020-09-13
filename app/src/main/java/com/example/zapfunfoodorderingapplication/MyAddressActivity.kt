package com.example.zapfunfoodorderingapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_my_address.*
import kotlinx.android.synthetic.main.activity_profile.*

class MyAddressActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_address)

        auth = FirebaseAuth.getInstance()

        deletebtn.setOnClickListener {
            Toast.makeText(this, "You are not allow to delete default address", Toast.LENGTH_SHORT).show()
        }

        backbtn_myaddress.setOnClickListener {
            val BurgerMenuFragment = BurgerMenuFragment()
            this.supportFragmentManager.beginTransaction().add(R.id.container, BurgerMenuFragment).commit()
        }

        val user = auth.currentUser

        val email_search = user?.email
        val uid_search = user?.uid

        if (email_search != null && uid_search != null) {
            FirebaseDatabase.getInstance().reference
                .child("User_Profile")
                .child(uid_search)
                .addValueEventListener(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {

                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        val map = p0.value as Map<String,Any>
                        recipientname.text = map["first_name"].toString() + " " + map["last_name"].toString()
                        phonenumber.text = map["phoneno"].toString()
                        address.text = map["address"].toString()
                        unitorfloor.text = map["floor"].toString()
                    }
                })
        }

    }
}