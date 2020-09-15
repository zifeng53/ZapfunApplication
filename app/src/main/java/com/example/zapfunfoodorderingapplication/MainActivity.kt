package com.example.zapfunfoodorderingapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

  class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //btm_nav start
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.btm_nav)
        val navController = findNavController(R.id.myNavHostFragment)
        bottomNavigationView.setupWithNavController(navController)
        fun showBottomNav() {
            btm_nav.visibility = View.VISIBLE
        }
        fun hideBottomNav() {
            btm_nav.visibility = View.GONE
        }
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.myMenuFragment -> showBottomNav()
                R.id.myOrderFragment -> showBottomNav()
                R.id.myHistoryFragment -> showBottomNav()
                else -> hideBottomNav()
            }
        }
        //btm_nav end
    }
}