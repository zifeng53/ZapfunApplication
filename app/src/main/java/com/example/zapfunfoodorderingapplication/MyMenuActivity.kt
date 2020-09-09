package com.example.zapfunfoodorderingapplication

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_my_menu.*

class MyMenuActivity : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_my_menu, container, false)
        val burgerMenu: ImageView = view.findViewById(R.id.imgBurgermenu)
        burgerMenu.setOnClickListener{view : View ->
            view.findNavController().navigate(R.id.action_myMenuFragment_to_fragment_burger_menu)}
        return view
    }
}