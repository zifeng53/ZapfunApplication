package com.example.zapfunfoodorderingapplication

import android.app.AlertDialog
import android.app.Notification
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zapfunfoodorderingapplication.EventBus.TodaySpecialClick
import com.example.zapfunfoodorderingapplication.callback.TodaySpecialMenuLoadCallback
import com.example.zapfunfoodorderingapplication.models.MenuTodaySpecialModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.appbar.*
import kotlinx.android.synthetic.main.fragment_my_history.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.fragment_my_menu.*

//class MainActivity : AppCompatActivity(), TodaySpecialMenuLoadCallback {
  class MainActivity : AppCompatActivity() {
    //lateinit var dialog: AlertDialog
    //lateinit var todaySpecialMenuLoadCallback:TodaySpecialMenuLoadCallback
    //lateinit var todaySpecialData:DatabaseReference

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

        /*dialog = SpotsDialog.Builder().setContext(this).build()
        todaySpecialData = FirebaseDatabase.getInstance().getReference().child("Dishes").child("Today_Special")
        todaySpecialMenuLoadCallback = this

        //View
        recycler_today_special.setHasFixedSize(true)
        recycler_today_special.layoutManager = LinearLayoutManager(this)

        getTodaySpecialFirebaseData()*/

        /*fun onStart() {
            super.onStart()
            EventBus.getDefault().unregister(this)
        }

        fun onStop() {
            EventBus.getDefault().unregister(this)
            super.onStop()
        }

        @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
        fun onTodaySpecialSelected(event: TodaySpecialClick) {
            if(event.isSuccess) {
                Toast.makeText(this, "Click to " + event.todaySpecial.name, Toast.LENGTH_SHORT).show()
            }
        }*/

    }

    /*private fun getTodaySpecialFirebaseData() {

        dialog.show()

        todaySpecialData.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                todaySpecialMenuLoadCallback.onTodaySpecialLoadFailed(p0.message)
            }

            override fun onDataChange(p0: DataSnapshot) {
                val todaySpecialItemGroups = ArrayList<MenuTodaySpecialModel>()
                for(myDataSnapshot in p0.children) {
                    val todaySpecialItemGroup = MenuTodaySpecialModel()
                    todaySpecialItemGroup.header
                }
            }
            
        })
    }

    override fun onTodaySpecialLoadSuccess(todaySpecialModelListMenu: List<MenuTodaySpecialModel>) {

    }*/


}