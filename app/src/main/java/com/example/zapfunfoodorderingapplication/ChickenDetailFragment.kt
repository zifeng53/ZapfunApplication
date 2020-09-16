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
import com.example.zapfunfoodorderingapplication.utils.ChickenDetailViewModel
import kotlinx.android.synthetic.main.fragment_chicken_detail.*

class ChickenDetailFragment : Fragment() {

    private lateinit var chickenDetailViewModel: ChickenDetailViewModel

    private var img_chicken:ImageView?=null
    private var txt_chicken:TextView?=null
    private var txt_chickenprice:TextView?=null
    //private var txt_chickendesc:TextView?=null
    //private var txt_chickenquantity:TextView?=null
    //private var btn_chickencart: Button?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        chickenDetailViewModel = ViewModelProviders.of(this).get(ChickenDetailViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_chicken_detail, container, false)

        val btnBack: TextView = root.findViewById(R.id.txtBack)
        btnBack.setOnClickListener{view : View ->
            view.findNavController().navigate(R.id.action_chickenDetailFragment_to_myMenuFragment)}

        initChickenViews(root)

        chickenDetailViewModel.getChickenDetailMutableLiveData().observe(viewLifecycleOwner, Observer {
            displayChickenInfo(it)
        })

        return root
    }

    private fun displayChickenInfo(it: MenuChickenModel?){
        Glide.with(requireContext()).load(it!!.image).into(imgChicken)
        txt_chicken!!.text = StringBuilder(it!!.name!!)
        txt_chickenprice!!.text = "RM " + StringBuilder(it!!.price!!.toString())
    }

    private fun initChickenViews(root: View?) {
        img_chicken = root!!.findViewById(R.id.imgChicken) as ImageView
        txt_chicken = root!!.findViewById(R.id.ChickenName) as TextView
        txt_chickenprice = root!!.findViewById(R.id.ChickenPrice) as TextView
        //txt_chickendesc = root!!.findViewById(R.id.foodDesc) as TextView
        //txt_chickenquantity = root!!.findViewById(R.id.foodQuantity) as TextView
        //btn_chickencart = root!!.findViewById(R.id.btnAddToCart) as Button
    }

}