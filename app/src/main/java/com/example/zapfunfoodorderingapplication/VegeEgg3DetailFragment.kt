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
import com.example.zapfunfoodorderingapplication.models.MenuVegeEgg3Model
import com.example.zapfunfoodorderingapplication.utils.VegeEgg3DetailViewModel
import kotlinx.android.synthetic.main.fragment_vege_egg3_detail.*

class VegeEgg3DetailFragment : Fragment() {

    private lateinit var vegeEgg3DetailViewModel: VegeEgg3DetailViewModel

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
        vegeEgg3DetailViewModel = ViewModelProviders.of(this).get(VegeEgg3DetailViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_vege_egg3_detail, container, false)

        val btnBack: TextView = root.findViewById(R.id.txtBack)
        btnBack.setOnClickListener{view : View ->
            view.findNavController().navigate(R.id.action_vegeEgg3DetailFragment_to_myMenuFragment)}

        initVegeEgg3Views(root)

        vegeEgg3DetailViewModel.getVegeEgg3DetailMutableLiveData().observe(viewLifecycleOwner, Observer {
            displayVegeEgg3Info(it)
        })

        return root
    }

    private fun displayVegeEgg3Info(it: MenuVegeEgg3Model?){
        Glide.with(requireContext()).load(it!!.image).into(imgPork1)
        txt_pork1!!.text = StringBuilder(it!!.name!!)
        txt_pork1price!!.text = "RM " + StringBuilder(it!!.price!!.toString())
    }

    private fun initVegeEgg3Views(root: View?) {
        img_pork1 = root!!.findViewById(R.id.imgPork1) as ImageView
        txt_pork1 = root!!.findViewById(R.id.Pork1Name) as TextView
        txt_pork1price = root!!.findViewById(R.id.Pork1Price) as TextView
        //txt_chickendesc = root!!.findViewById(R.id.foodDesc) as TextView
        //txt_chickenquantity = root!!.findViewById(R.id.foodQuantity) as TextView
        //btn_chickencart = root!!.findViewById(R.id.btnAddToCart) as Button
    }

}