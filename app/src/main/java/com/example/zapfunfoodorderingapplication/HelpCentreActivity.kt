package com.example.zapfunfoodorderingapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_contact_us.*
import kotlinx.android.synthetic.main.activity_help_centre.*

class HelpCentreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help_centre)

        backbtn_help.setOnClickListener {
            val BurgerMenuFragment = BurgerMenuFragment()
            this.supportFragmentManager.beginTransaction().add(R.id.container, BurgerMenuFragment).commit()
        }
    }
}