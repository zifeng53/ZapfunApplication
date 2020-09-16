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
import com.example.zapfunfoodorderingapplication.models.MenuVegeEgg1Model
import com.example.zapfunfoodorderingapplication.utils.VegeEgg1DetailViewModel
import kotlinx.android.synthetic.main.fragment_vege_egg1_detail.*

class VegeEgg1DetailFragment : Fragment() {

    private lateinit var vegeEgg1DetailViewModel: VegeEgg1DetailViewModel

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
        vegeEgg1DetailViewModel = ViewModelProviders.of(this).get(VegeEgg1DetailViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_vege_egg1_detail, container, false)

        val btnBack: TextView = root.findViewById(R.id.txtBack)
        btnBack.setOnClickListener{view : View ->
            view.findNavController().navigate(R.id.action_vegeEgg1DetailFragment_to_myMenuFragment)}

        initVegeEgg1Views(root)

        vegeEgg1DetailViewModel.getVegeEgg1DetailMutableLiveData().observe(viewLifecycleOwner, Observer {
            displayVegeEgg1Info(it)
        })

        return root
    }

    private fun displayVegeEgg1Info(it: MenuVegeEgg1Model?){
        Glide.with(requireContext()).load(it!!.image).into(imgPork1)
        txt_pork1!!.text = StringBuilder(it!!.name!!)
        txt_pork1price!!.text = "RM " + StringBuilder(it!!.price!!.toString())
    }

    private fun initVegeEgg1Views(root: View?) {
        img_pork1 = root!!.findViewById(R.id.imgPork1) as ImageView
        txt_pork1 = root!!.findViewById(R.id.Pork1Name) as TextView
        txt_pork1price = root!!.findViewById(R.id.Pork1Price) as TextView
        //txt_chickendesc = root!!.findViewById(R.id.foodDesc) as TextView
        //txt_chickenquantity = root!!.findViewById(R.id.foodQuantity) as TextView
        //btn_chickencart = root!!.findViewById(R.id.btnAddToCart) as Button
    }

}