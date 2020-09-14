package com.example.zapfunfoodorderingapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_address.*
import kotlinx.android.synthetic.main.activity_register_account.*

class AddressActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {

        auth = FirebaseAuth.getInstance()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address)

        backbtn_address.setOnClickListener {
            startActivity(Intent(this, MyAddressActivity::class.java))
        }

        cancelbtn.setOnClickListener {
            startActivity(Intent(this, MyAddressActivity::class.java))
        }

        confirmbtn.setOnClickListener {
            updatedata()
        }
    }

    private fun updatedata() {

        if(recipientname.text.toString().isEmpty()) {
            recipientname.error = "Enter recipient name"
            recipientname.requestFocus()
            return
        }

        if(phonenumber.text.toString().isEmpty()) {
            phonenumber.error = "Enter recipient phone number"
            phonenumber.requestFocus()
            return
        }

        if(address_address.text.toString().isEmpty()) {
            address_address.error = "Enter recipient address"
            address_address.requestFocus()
            return
        }

        if(floorunit_address.text.toString().isEmpty()) {
            floorunit_address.error = "Enter recipient floor and unit"
            floorunit_address.requestFocus()
            return
        }
    }
}