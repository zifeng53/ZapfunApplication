package com.example.zapfunfoodorderingapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_my_history.*

class MainActivity : AppCompatActivity() {

    // create three objects for fragments
    //lateinit var welcome: fragment_welcome
    lateinit var myMenuFragment: MyMenuFragment
    lateinit var myOrderFragment: MyOrderFragment
    lateinit var myHistoryFragment: MyHistoryFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigation : BottomNavigationView = findViewById(R.id.btm_nav)

        // set Welcome as default activity, when app is open the Welcome activity will show all that time
        /*welcome = fragment_welcome()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout,welcome)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()*/


        // set Menu as default fragment, when app is open the Menu fragment will show all that time
        myMenuFragment = MyMenuFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout,myMenuFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()

        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {

                //create three fragments

                R.id.myMenu -> {
                    myMenuFragment = MyMenuFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout,myMenuFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }

                R.id.myOrder -> {
                    myOrderFragment = MyOrderFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout,myOrderFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }

                R.id.myHistory -> {
                    myHistoryFragment = MyHistoryFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout,myHistoryFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }

            }
            true
        }

    }

}