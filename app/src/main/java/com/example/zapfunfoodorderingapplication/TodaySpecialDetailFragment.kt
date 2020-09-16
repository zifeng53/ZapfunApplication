package com.example.zapfunfoodorderingapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.zapfunfoodorderingapplication.models.*
import com.example.zapfunfoodorderingapplication.utils.*
import kotlinx.android.synthetic.main.fragment_today_special_detail.*

class TodaySpecialDetailFragment : Fragment() {

    private lateinit var todaySpecialDetailViewModel: TodaySpecialDetailViewModel
    //private lateinit var chickenDetailViewModel: ChickenDetailViewModel
    /*private lateinit var pork1DetailViewModel: Pork1DetailViewModel
    private lateinit var pork2DetailViewModel: Pork2DetailViewModel
    private lateinit var fishDetailViewModel: FishDetailViewModel
    private lateinit var vegeEgg1DetailViewModel: VegeEgg1DetailViewModel
    private lateinit var vegeEgg2DetailViewModel: VegeEgg2DetailViewModel
    private lateinit var vegeEgg3DetailViewModel: VegeEgg3DetailViewModel
    private lateinit var vegeEgg4DetailViewModel: VegeEgg4DetailViewModel*/

    private var img_menu:ImageView?=null
    private var txt_name:TextView?=null
    private var txt_price:TextView?=null
    //private var txt_desc:TextView?=null
    //private var txt_quantity:TextView?=null
    //private var btn_cart: Button?=null

    /*private var img_chicken:ImageView?=null
    private var txt_chicken:TextView?=null
    private var txt_chickenprice:TextView?=null
    private var txt_chickendesc:TextView?=null
    private var txt_chickenquantity:TextView?=null
    private var btn_chickencart: Button?=null*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        todaySpecialDetailViewModel = ViewModelProviders.of(this).get(TodaySpecialDetailViewModel::class.java)
        //chickenDetailViewModel = ViewModelProviders.of(this).get(ChickenDetailViewModel::class.java)
        /*pork1DetailViewModel = ViewModelProviders.of(this).get(Pork1DetailViewModel::class.java)
        pork2DetailViewModel = ViewModelProviders.of(this).get(Pork2DetailViewModel::class.java)
        fishDetailViewModel = ViewModelProviders.of(this).get(FishDetailViewModel::class.java)
        vegeEgg1DetailViewModel = ViewModelProviders.of(this).get(VegeEgg1DetailViewModel::class.java)
        vegeEgg2DetailViewModel = ViewModelProviders.of(this).get(VegeEgg2DetailViewModel::class.java)
        vegeEgg3DetailViewModel = ViewModelProviders.of(this).get(VegeEgg3DetailViewModel::class.java)
        vegeEgg4DetailViewModel = ViewModelProviders.of(this).get(VegeEgg4DetailViewModel::class.java)*/

        val root = inflater.inflate(R.layout.fragment_today_special_detail, container, false)

        val btnBack: TextView = root.findViewById(R.id.txtBack)
        btnBack.setOnClickListener{view : View ->
            view.findNavController().navigate(R.id.action_todaySpecialDetailFragment_to_myMenuFragment)}

        initViews(root)
        //initChickenViews(root)

        todaySpecialDetailViewModel.getTodaySpecialDetailMutableLiveData().observe(viewLifecycleOwner, Observer {
            displayTodaySpecialInfo(it)
        })
        /*chickenDetailViewModel.getChickenDetailMutableLiveData().observe(viewLifecycleOwner, Observer {
            displayChickenInfo(it)
        })
        pork1DetailViewModel.getPork1DetailMutableLiveData().observe(viewLifecycleOwner, Observer {
            displayPork1Info(it)
        })
        pork2DetailViewModel.getPork2DetailMutableLiveData().observe(viewLifecycleOwner, Observer {
            displayPork2Info(it)
        })
        fishDetailViewModel.getFishDetailMutableLiveData().observe(viewLifecycleOwner, Observer {
            displayFishInfo(it)
        })
        vegeEgg1DetailViewModel.getVegeEgg1DetailMutableLiveData().observe(viewLifecycleOwner, Observer {
            displayVegeEgg1Info(it)
        })
        vegeEgg2DetailViewModel.getVegeEgg2DetailMutableLiveData().observe(viewLifecycleOwner, Observer {
            displayVegeEgg2Info(it)
        })
        vegeEgg3DetailViewModel.getVegeEgg3DetailMutableLiveData().observe(viewLifecycleOwner, Observer {
            displayVegeEgg3Info(it)
        })
        vegeEgg4DetailViewModel.getVegeEgg4DetailMutableLiveData().observe(viewLifecycleOwner, Observer {
            displayVegeEgg4Info(it)
        })*/

        return root
    }

    private fun displayTodaySpecialInfo(it: MenuTodaySpecialModel?){
        Glide.with(requireContext()).load(it!!.image).into(imgTodaySpecial)
        txt_name!!.text = StringBuilder(it!!.name!!)
        txt_price!!.text = "RM " + StringBuilder(it!!.price!!.toString())
    }
    /*private fun displayChickenInfo(it: MenuChickenModel?){
        Glide.with(requireContext()).load(it!!.image).into(imgFood)
        txt_chicken!!.text = StringBuilder(it!!.name!!)
        txt_chickenprice!!.text = StringBuilder(it!!.price!!.toString())
    }
    private fun displayPork1Info(it: MenuPork1Model?){
        Glide.with(requireContext()).load(it!!.image).into(imgFood)
        txt_name!!.text = StringBuilder(it!!.name!!)
        txt_price!!.text = StringBuilder(it!!.price!!.toString())
    }
    private fun displayPork2Info(it: MenuPork2Model?){
        Glide.with(requireContext()).load(it!!.image).into(imgFood)
        txt_name!!.text = StringBuilder(it!!.name!!)
        txt_price!!.text = StringBuilder(it!!.price!!.toString())
    }
    private fun displayFishInfo(it: MenuFishModel?){
        Glide.with(requireContext()).load(it!!.image).into(imgFood)
        txt_name!!.text = StringBuilder(it!!.name!!)
        txt_price!!.text = StringBuilder(it!!.price!!.toString())
    }
    private fun displayVegeEgg1Info(it: MenuVegeEgg1Model?){
        Glide.with(requireContext()).load(it!!.image).into(imgFood)
        txt_name!!.text = StringBuilder(it!!.name!!)
        txt_price!!.text = StringBuilder(it!!.price!!.toString())
    }
    private fun displayVegeEgg2Info(it: MenuVegeEgg2Model?){
        Glide.with(requireContext()).load(it!!.image).into(imgFood)
        txt_name!!.text = StringBuilder(it!!.name!!)
        txt_price!!.text = StringBuilder(it!!.price!!.toString())
    }
    private fun displayVegeEgg3Info(it: MenuVegeEgg3Model?){
        Glide.with(requireContext()).load(it!!.image).into(imgFood)
        txt_name!!.text = StringBuilder(it!!.name!!)
        txt_price!!.text = StringBuilder(it!!.price!!.toString())
    }
    private fun displayVegeEgg4Info(it: MenuVegeEgg4Model?){
        Glide.with(requireContext()).load(it!!.image).into(imgFood)
        txt_name!!.text = StringBuilder(it!!.name!!)
        txt_price!!.text = StringBuilder(it!!.price!!.toString())
    }*/

    private fun initViews(root: View?) {
        img_menu = root!!.findViewById(R.id.imgTodaySpecial) as ImageView
        txt_name = root!!.findViewById(R.id.TodaySpecialName) as TextView
        txt_price = root!!.findViewById(R.id.TodaySpecialPrice) as TextView
        //txt_desc = root!!.findViewById(R.id.foodDesc) as TextView
        //txt_quantity = root!!.findViewById(R.id.foodQuantity) as TextView
        //btn_cart = root!!.findViewById(R.id.btnAddToCart) as Button
    }
    /*private fun initChickenViews(root: View?) {
        img_chicken = root!!.findViewById(R.id.imgFood) as ImageView
        txt_chicken = root!!.findViewById(R.id.foodName) as TextView
        txt_chickenprice = root!!.findViewById(R.id.foodPrice) as TextView
        //txt_chickendesc = root!!.findViewById(R.id.foodDesc) as TextView
        //txt_chickenquantity = root!!.findViewById(R.id.foodQuantity) as TextView
        //btn_chickencart = root!!.findViewById(R.id.btnAddToCart) as Button
    }*/

}