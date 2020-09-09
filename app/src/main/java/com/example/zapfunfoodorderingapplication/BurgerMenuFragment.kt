package com.example.zapfunfoodorderingapplication

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.fragment_my_menu.*

class BurgerMenuFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_burger_menu, container, false)
        val btnBack: TextView = view.findViewById(R.id.txtBack)
        btnBack.setOnClickListener{view : View ->
            view.findNavController().navigate(R.id.action_burgerMenuFragment_to_myMenuFragment)}
        val btnOrder: FrameLayout = view.findViewById(R.id.orderFrame)
        btnOrder.setOnClickListener{view : View ->
            view.findNavController().navigate(R.id.action_burgerMenuFragment_to_myOrderFragment)}
        val btnHistory: FrameLayout = view.findViewById(R.id.historyFrame)
        btnHistory.setOnClickListener{view : View ->
            view.findNavController().navigate(R.id.action_burgerMenuFragment_to_myHistoryFragment)}
        val btnAddress: FrameLayout = view.findViewById(R.id.addressFrame)
        btnAddress.setOnClickListener{view : View ->
            view.findNavController().navigate(R.id.action_burgerMenuFragment_to_myAddressFragment)}
        val btnProfile: FrameLayout = view.findViewById(R.id.profileFrame)
        btnProfile.setOnClickListener{view : View ->
            view.findNavController().navigate(R.id.action_burgerMenuFragment_to_myProfileFragment)}
        val btnLogout: FrameLayout = view.findViewById(R.id.logoutFrame)
        btnLogout.setOnClickListener{view : View ->
            view.findNavController().navigate(R.id.action_burgerMenuFragment_to_loginActivity)}
        val btnHelp: FrameLayout = view.findViewById(R.id.helpFrame)
        btnHelp.setOnClickListener{view : View ->
            view.findNavController().navigate(R.id.action_burgerMenuFragment_to_helpCentreFragment)}
        val btnAbout: FrameLayout = view.findViewById(R.id.aboutFrame)
        btnAbout.setOnClickListener{view : View ->
            view.findNavController().navigate(R.id.action_burgerMenuFragment_to_aboutUsFragment)}
        val btnContact: FrameLayout = view.findViewById(R.id.contactFrame)
        btnContact.setOnClickListener{view : View ->
            view.findNavController().navigate(R.id.action_burgerMenuFragment_to_contactUsFragment)}
        return view
    }
}