package com.example.zapfunfoodorderingapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_card_checkout.*

class CardCheckoutActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_checkout)
        button14.setOnClickListener {
            if (editTextTextPersonName29.text.toString().isEmpty()){
                editTextTextPersonName29.error = "Please enter card number"
            }
            if (editTextTextPersonName37.text.toString().isEmpty()){
                editTextTextPersonName37.error = "Please enter card holder's name"
            }
            if (editTextTextPersonName38.text.toString().isEmpty()){
                editTextTextPersonName38.error = "Please enter card expiry date"
            }
            if (editTextTextPersonName39.text.toString().isEmpty()){
                editTextTextPersonName39.error = "Please enter CVV number"
            }
            else {
                clearCart()
                Toast.makeText(applicationContext, "PAID SUCCESSFULLY!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
        button13.setOnClickListener {
            finish()
            //startActivity(Intent(this, MyOrderActivity::class.java))
        }
    }

    fun clearCart(){
        auth = FirebaseAuth.getInstance()

        val ref = FirebaseDatabase.getInstance().reference
            .child("Cart")

        ref.removeValue()
    }

}