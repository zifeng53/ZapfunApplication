package com.example.zapfunfoodorderingapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.zapfunfoodorderingapplication.adapters.MenuTodaySpecialAdapter
import com.example.zapfunfoodorderingapplication.utils.MyMenuViewModel

class MyMenuFragment : Fragment() {

    private lateinit var myMenuViewModel: MyMenuViewModel
    var  recyclerView:RecyclerView?=null

   override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_my_menu, container, false)
        val burgerMenu: ImageView = view.findViewById(R.id.imgBurgermenu)
        burgerMenu.setOnClickListener{view : View ->
            view.findNavController().navigate(R.id.action_myMenuFragment_to_burgerMenuFragment)}

        myMenuViewModel =
            ViewModelProviders.of(this).get(MyMenuViewModel::class.java)

        initTodaySpecialView(view)
        //initChickenView(view)
        //Bind data
        myMenuViewModel.todaySpecialList.observe(viewLifecycleOwner, Observer {
            val listData = it
            val adapter = MenuTodaySpecialAdapter(requireContext(), listData)
            recyclerView!!.adapter = adapter
        })
        return view
    }

    private fun initTodaySpecialView(view:View) {
        recyclerView = view.findViewById(R.id.recycler_today_special) as RecyclerView
        recyclerView!!.setHasFixedSize(true)
        recyclerView!!.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
    }

    /*private fun initChickenView(view:View) {
        recyclerView = view.findViewById(R.id.recycler_chicken) as RecyclerView
        recyclerView!!.setHasFixedSize(true)
        val layoutManager = GridLayoutManager(context, 4)
        layoutManager.orientation = RecyclerView.VERTICAL
        //recyclerView!!.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
    }*/
}