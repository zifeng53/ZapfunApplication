package com.example.zapfunfoodorderingapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_about_us.*

//import kotlinx.android.synthetic.main.activity_about_us.*

class AboutUsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)

        backbtn_aboutus.setOnClickListener {
            val BurgerMenuFragment = BurgerMenuFragment()
            this.supportFragmentManager.beginTransaction().add(R.id.container, BurgerMenuFragment).commit()
        }
    }
}