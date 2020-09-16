package com.example.zapfunfoodorderingapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.zapfunfoodorderingapplication.models.MenuTodaySpecialModel
import com.example.zapfunfoodorderingapplication.utils.MenuDetailViewModel
import kotlinx.android.synthetic.main.fragment_menu_detail.*

class MenuDetailFragment : Fragment() {

    private lateinit var menuDetailViewModel: MenuDetailViewModel

    private var img_menu:ImageView?=null
    private var txt_name:TextView?=null
    private var txt_price:TextView?=null
    private var txt_desc:TextView?=null
    private var txt_quantity:TextView?=null
    private var btn_cart: Button?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        menuDetailViewModel = ViewModelProviders.of(this).get(MenuDetailViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_menu_detail, container, false)
        initViews(root)
        menuDetailViewModel.getMenuDetailMutableLiveData().observe(viewLifecycleOwner, Observer {
            displayInfo(it)
        })
        return root
    }

    private fun displayInfo(it: MenuTodaySpecialModel?){
        Glide.with(requireContext()).load(it!!.image).into(imgFood)
        txt_name!!.text = StringBuilder(it!!.name!!)
        txt_price!!.text = StringBuilder(it!!.price!!.toString())
    }

    private fun initViews(root: View?) {
        img_menu = root!!.findViewById(R.id.imgFood) as ImageView
        txt_name = root!!.findViewById(R.id.foodName) as TextView
        txt_price = root!!.findViewById(R.id.foodPrice) as TextView
        txt_desc = root!!.findViewById(R.id.foodDesc) as TextView
        txt_quantity = root!!.findViewById(R.id.foodQuantity) as TextView
        btn_cart = root!!.findViewById(R.id.btnAddToCart) as Button


    }

}