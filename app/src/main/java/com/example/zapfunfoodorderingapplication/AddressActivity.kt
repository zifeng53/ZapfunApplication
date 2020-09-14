package com.example.zapfunfoodorderingapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_address.*
import kotlinx.android.synthetic.main.activity_register_account.*
import kotlinx.android.synthetic.main.fragment_my_profile_contact_info_edit.*
import kotlin.math.floor

class AddressActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var phonenumber_address: EditText
    private lateinit var floor_address: EditText
    private lateinit var recipient_name: EditText
    private lateinit var address1_address: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address)

        auth = FirebaseAuth.getInstance()

        phonenumber_address = findViewById(R.id.phonenumber)
        floor_address = findViewById(R.id.floorunit_address)
        recipient_name = findViewById(R.id.recipientname)
        address1_address = findViewById(R.id.address_address)

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

        val phonenumber_address = phonenumber_address.text.toString()
        val floor_address = floor_address.text.toString()
        val recipient_name = recipient_name.text.toString()
        val address1_address = address1_address.text.toString()

        val user = auth.currentUser
        val userid = user?.uid
        val database = FirebaseDatabase.getInstance().getReference("User_Profile")

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

        if (userid != null) {
            database.child(userid).child("phoneno").setValue(phonenumber_address)
            database.child(userid).child("address").setValue(address1_address)
            database.child(userid).child("floor").setValue(floor_address)
            database.child(userid).child("last_name").setValue(recipient_name)
        }

        startActivity(Intent(this, MyAddressActivity::class.java))
        finish()
    }
}