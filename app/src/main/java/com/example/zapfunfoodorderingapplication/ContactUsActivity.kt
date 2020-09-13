package com.example.zapfunfoodorderingapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_contact_us.*
import kotlinx.android.synthetic.main.activity_profile.*

class ContactUsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_us)

        backbtn_contactus2.setOnClickListener {
            val BurgerMenuFragment = BurgerMenuFragment()
            this.supportFragmentManager.beginTransaction().add(R.id.container, BurgerMenuFragment).commit()
        }
    }
}