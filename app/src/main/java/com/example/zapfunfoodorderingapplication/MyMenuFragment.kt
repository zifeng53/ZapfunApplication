package com.example.zapfunfoodorderingapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.zapfunfoodorderingapplication.adapters.*
import com.example.zapfunfoodorderingapplication.utils.*

class   MyMenuFragment : Fragment() {

    private lateinit var todaySpecialViewModel: TodaySpecialMenuViewModel
    var recyclerTodaySpecialView:RecyclerView?=null

    private lateinit var chickenMenuViewModel: ChickenMenuViewModel
    var recyclerChickenView:RecyclerView?=null

    private lateinit var pork1MenuViewModel: Pork1MenuViewModel
    var recyclerPork1View:RecyclerView?=null

    private lateinit var pork2MenuViewModel: Pork2MenuViewModel
    var recyclerPork2View:RecyclerView?=null

    private lateinit var fishMenuViewModel: FishMenuViewModel
    var recyclerFishView:RecyclerView?=null

    private lateinit var vegeEgg1MenuViewModel: VegeEgg1MenuViewModel
    var recyclerVegeEgg1View:RecyclerView?=null

    private lateinit var vegeEgg2MenuViewModel: VegeEgg2MenuViewModel
    var recyclerVegeEgg2View:RecyclerView?=null

    private lateinit var vegeEgg3MenuViewModel: VegeEgg3MenuViewModel
    var recyclerVegeEgg3View:RecyclerView?=null

    private lateinit var vegeEgg4MenuViewModel: VegeEgg4MenuViewModel
    var recyclerVegeEgg4View:RecyclerView?=null

   override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_my_menu, container, false)
        val burgerMenu: ImageView = view.findViewById(R.id.imgBurgermenu)
        burgerMenu.setOnClickListener{view : View ->
            view.findNavController().navigate(R.id.action_myMenuFragment_to_burgerMenuFragment)}

        val txtRice: TextView = view.findViewById(R.id.lblConfirmRice)
        val radioGroup: RadioGroup = view.findViewById(R.id.radioGroup)
        radioGroup.setOnCheckedChangeListener{group, checkedID ->
            if(checkedID == R.id.rbNone) {
                txtRice.text = "No Rice"
            }

            if(checkedID == R.id.rbLess) {
                txtRice.text = "Less Rice"
            }

            if(checkedID == R.id.rbNormal) {
                txtRice.text = "Normal Rice"
            }

            if(checkedID == R.id.rbExtra) {
                txtRice.text = "Add Rice (+RM0.50)"
            }
        }

       todaySpecialViewModel =
            ViewModelProviders.of(this).get(TodaySpecialMenuViewModel::class.java)

       chickenMenuViewModel =
           ViewModelProviders.of(this).get(ChickenMenuViewModel::class.java)

       pork1MenuViewModel =
           ViewModelProviders.of(this).get(Pork1MenuViewModel::class.java)

       pork2MenuViewModel =
           ViewModelProviders.of(this).get(Pork2MenuViewModel::class.java)

       fishMenuViewModel =
           ViewModelProviders.of(this).get(FishMenuViewModel::class.java)

       vegeEgg1MenuViewModel =
           ViewModelProviders.of(this).get(VegeEgg1MenuViewModel::class.java)

       vegeEgg2MenuViewModel =
           ViewModelProviders.of(this).get(VegeEgg2MenuViewModel::class.java)

       vegeEgg3MenuViewModel =
           ViewModelProviders.of(this).get(VegeEgg3MenuViewModel::class.java)

       vegeEgg4MenuViewModel =
           ViewModelProviders.of(this).get(VegeEgg4MenuViewModel::class.java)

        initTodaySpecialView(view)
        initChickenView(view)
        initPork1View(view)
        initPork2View(view)
        initFishView(view)
        initVegeEgg1View(view)
        initVegeEgg2View(view)
        initVegeEgg3View(view)
        initVegeEgg4View(view)

        //Bind data
       todaySpecialViewModel.todaySpecialList.observe(viewLifecycleOwner, Observer {
           val listData = it
           val adapter = TodaySpecialAdapter(requireContext(), listData)
           recyclerTodaySpecialView!!.adapter = adapter
       })

       chickenMenuViewModel.chickenList.observe(viewLifecycleOwner, Observer {
           val listData = it
           val adapter = ChickenAdapter(requireContext(), listData)
           recyclerChickenView!!.adapter = adapter
       })

       pork1MenuViewModel.pork1List.observe(viewLifecycleOwner, Observer {
           val listData = it
           val adapter = Pork1Adapter(requireContext(), listData)
           recyclerPork1View!!.adapter = adapter
       })

       pork2MenuViewModel.pork2List.observe(viewLifecycleOwner, Observer {
           val listData = it
           val adapter = Pork2Adapter(requireContext(), listData)
           recyclerPork2View!!.adapter = adapter
       })

       fishMenuViewModel.fishList.observe(viewLifecycleOwner, Observer {
           val listData = it
           val adapter = FishAdapter(requireContext(), listData)
           recyclerFishView!!.adapter = adapter
       })

       vegeEgg1MenuViewModel.vegeEgg1List.observe(viewLifecycleOwner, Observer {
           val listData = it
           val adapter = VegeEgg1Adapter(requireContext(), listData)
           recyclerVegeEgg1View!!.adapter = adapter
       })

       vegeEgg2MenuViewModel.vegeEgg2List.observe(viewLifecycleOwner, Observer {
           val listData = it
           val adapter = VegeEgg2Adapter(requireContext(), listData)
           recyclerVegeEgg2View!!.adapter = adapter
       })

       vegeEgg3MenuViewModel.vegeEgg3List.observe(viewLifecycleOwner, Observer {
           val listData = it
           val adapter = VegeEgg3Adapter(requireContext(), listData)
           recyclerVegeEgg3View!!.adapter = adapter
       })

       vegeEgg4MenuViewModel.vegeEgg4List.observe(viewLifecycleOwner, Observer {
           val listData = it
           val adapter = VegeEgg4Adapter(requireContext(), listData)
           recyclerVegeEgg4View!!.adapter = adapter
       })
        return view
    }

    private fun initTodaySpecialView(view:View) {
        recyclerTodaySpecialView = view.findViewById(R.id.recycler_today_special) as RecyclerView
        recyclerTodaySpecialView!!.setHasFixedSize(true)
        recyclerTodaySpecialView!!.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
    }

    private fun initChickenView(view:View) {
        recyclerChickenView = view.findViewById(R.id.recycler_chicken) as RecyclerView
        recyclerChickenView!!.setHasFixedSize(true)
        recyclerChickenView!!.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
    }

    private fun initPork1View(view:View) {
        recyclerPork1View = view.findViewById(R.id.recycler_pork1) as RecyclerView
        recyclerPork1View!!.setHasFixedSize(true)
        recyclerPork1View!!.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
    }

    private fun initPork2View(view:View) {
        recyclerPork2View = view.findViewById(R.id.recycler_pork2) as RecyclerView
        recyclerPork2View!!.setHasFixedSize(true)
        recyclerPork2View!!.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
    }

    private fun initFishView(view:View) {
        recyclerFishView = view.findViewById(R.id.recycler_fish) as RecyclerView
        recyclerFishView!!.setHasFixedSize(true)
        recyclerFishView!!.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
    }

    private fun initVegeEgg1View(view:View) {
        recyclerVegeEgg1View = view.findViewById(R.id.recycler_vege_egg1) as RecyclerView
        recyclerVegeEgg1View!!.setHasFixedSize(true)
        recyclerVegeEgg1View!!.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
    }

    private fun initVegeEgg2View(view:View) {
        recyclerVegeEgg2View = view.findViewById(R.id.recycler_vege_egg2) as RecyclerView
        recyclerVegeEgg2View!!.setHasFixedSize(true)
        recyclerVegeEgg2View!!.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
    }

    private fun initVegeEgg3View(view:View) {
        recyclerVegeEgg3View = view.findViewById(R.id.recycler_vege_egg3) as RecyclerView
        recyclerVegeEgg3View!!.setHasFixedSize(true)
        recyclerVegeEgg3View!!.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
    }

    private fun initVegeEgg4View(view:View) {
        recyclerVegeEgg4View = view.findViewById(R.id.recycler_vege_egg4) as RecyclerView
        recyclerVegeEgg4View!!.setHasFixedSize(true)
        recyclerVegeEgg4View!!.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
    }
}