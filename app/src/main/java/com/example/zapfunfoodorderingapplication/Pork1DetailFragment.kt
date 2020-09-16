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
import com.example.zapfunfoodorderingapplication.models.MenuChickenModel
import com.example.zapfunfoodorderingapplication.models.MenuPork1Model
import com.example.zapfunfoodorderingapplication.utils.ChickenDetailViewModel
import com.example.zapfunfoodorderingapplication.utils.Pork1DetailViewModel
import kotlinx.android.synthetic.main.fragment_chicken_detail.*
import kotlinx.android.synthetic.main.fragment_chicken_detail.imgChicken
import kotlinx.android.synthetic.main.fragment_pork1_detail.*


class Pork1DetailFragment : Fragment() {

    private lateinit var pork1DetailViewModel: Pork1DetailViewModel

    private var img_pork1: ImageView?=null
    private var txt_pork1: TextView?=null
    private var txt_pork1price: TextView?=null
    //private var txt_chickendesc:TextView?=null
    //private var txt_chickenquantity:TextView?=null
    //private var btn_chickencart: Button?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        pork1DetailViewModel = ViewModelProviders.of(this).get(Pork1DetailViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_pork1_detail, container, false)

        val btnBack: TextView = root.findViewById(R.id.txtBack)
        btnBack.setOnClickListener{view : View ->
            view.findNavController().navigate(R.id.action_pork1DetailFragment_to_myMenuFragment)}

        initPork1Views(root)

        pork1DetailViewModel.getPork1DetailMutableLiveData().observe(viewLifecycleOwner, Observer {
            displayPork1Info(it)
        })

        return root
    }

    private fun displayPork1Info(it: MenuPork1Model?){
        Glide.with(requireContext()).load(it!!.image).into(imgPork1)
        txt_pork1!!.text = StringBuilder(it!!.name!!)
        txt_pork1price!!.text = "RM " + StringBuilder(it!!.price!!.toString())
    }

    private fun initPork1Views(root: View?) {
        img_pork1 = root!!.findViewById(R.id.imgPork1) as ImageView
        txt_pork1 = root!!.findViewById(R.id.Pork1Name) as TextView
        txt_pork1price = root!!.findViewById(R.id.Pork1Price) as TextView
        //txt_chickendesc = root!!.findViewById(R.id.foodDesc) as TextView
        //txt_chickenquantity = root!!.findViewById(R.id.foodQuantity) as TextView
        //btn_chickencart = root!!.findViewById(R.id.btnAddToCart) as Button
    }

}