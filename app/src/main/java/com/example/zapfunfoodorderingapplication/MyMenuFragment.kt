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
import com.example.zapfunfoodorderingapplication.Common.Common
import com.example.zapfunfoodorderingapplication.Common.SpacesItemDecoration
import com.example.zapfunfoodorderingapplication.adapters.MenuChickenAdapter
import com.example.zapfunfoodorderingapplication.adapters.MenuPork1Adapter
import com.example.zapfunfoodorderingapplication.adapters.MenuTodaySpecialAdapter
import com.example.zapfunfoodorderingapplication.utils.ChickenMenuViewModel
import com.example.zapfunfoodorderingapplication.utils.MyMenuViewModel
import com.example.zapfunfoodorderingapplication.utils.Pork1MenuViewModel
import kotlinx.android.synthetic.main.fragment_my_menu.*

class   MyMenuFragment : Fragment() {

    private lateinit var myMenuViewModel: MyMenuViewModel
    var  recyclerView:RecyclerView?=null

    private lateinit var chickenMenuViewModel: ChickenMenuViewModel
    var recyclerChickenView:RecyclerView?=null
    //private var chickenAdapter:MenuChickenAdapter?=null

    private lateinit var pork1MenuViewModel: Pork1MenuViewModel
    var recyclerPork1View:RecyclerView?=null

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

       chickenMenuViewModel =
           ViewModelProviders.of(this).get(ChickenMenuViewModel::class.java)

       pork1MenuViewModel =
           ViewModelProviders.of(this).get(Pork1MenuViewModel::class.java)

        initTodaySpecialView(view)
        initChickenView(view)
        initPork1View(view)
        //Bind data
        myMenuViewModel.todaySpecialList.observe(viewLifecycleOwner, Observer {
            val listData = it
            val adapter = MenuTodaySpecialAdapter(requireContext(), listData)
            recyclerView!!.adapter = adapter
        })

       chickenMenuViewModel.chickenList.observe(viewLifecycleOwner, Observer {
           val listData = it
           val adapter = MenuChickenAdapter(requireContext(), listData)
           recyclerChickenView!!.adapter = adapter
       })

       pork1MenuViewModel.pork1List.observe(viewLifecycleOwner, Observer {
           val listData = it
           val adapter = MenuPork1Adapter(requireContext(), listData)
           recyclerPork1View!!.adapter = adapter
       })
        return view
    }

    private fun initTodaySpecialView(view:View) {
        recyclerView = view.findViewById(R.id.recycler_today_special) as RecyclerView
        recyclerView!!.setHasFixedSize(true)
        recyclerView!!.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
    }

    private fun initChickenView(view:View) {
        recyclerChickenView = view.findViewById(R.id.recycler_chicken) as RecyclerView
        recyclerChickenView!!.setHasFixedSize(true)
        /*val layoutManager = GridLayoutManager(context, 2)
        layoutManager.orientation = RecyclerView.VERTICAL
        layoutManager.spanSizeLookup = object: GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if(chickenAdapter != null)
                {
                    when(chickenAdapter!!.getItemViewType(position)) {
                        Common.DEFAULT_COLUMN_COUNT -> 1
                        Common.FULL_WIDTH_COLUMN -> 2
                        else -> 1
                    }
                } else
                    -1
            }
        }
        recyclerChickenView!!.layoutManager = layoutManager
        recyclerChickenView!!.addItemDecoration(SpacesItemDecoration(8))*/
        recyclerChickenView!!.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
    }

    private fun initPork1View(view:View) {
        recyclerPork1View = view.findViewById(R.id.recycler_pork1) as RecyclerView
        recyclerPork1View!!.setHasFixedSize(true)
        recyclerPork1View!!.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
    }
}