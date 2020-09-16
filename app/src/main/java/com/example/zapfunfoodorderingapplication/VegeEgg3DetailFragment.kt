package com.example.zapfunfoodorderingapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.zapfunfoodorderingapplication.models.CartMenuModel
import com.example.zapfunfoodorderingapplication.models.MenuVegeEgg3Model
import com.example.zapfunfoodorderingapplication.utils.VegeEgg3DetailViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_fish_detail.*
import kotlinx.android.synthetic.main.fragment_vege_egg3_detail.*
import kotlinx.android.synthetic.main.fragment_vege_egg3_detail.imgPork1
import kotlinx.android.synthetic.main.fragment_vege_egg3_detail.txtUserID

class VegeEgg3DetailFragment : Fragment() {

    private lateinit var vegeEgg3DetailViewModel: VegeEgg3DetailViewModel

    private var img_pork1: ImageView?=null
    private var txt_pork1: TextView?=null
    private var txt_pork1price: TextView?=null
    private var userId: TextView? = null

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true

        auth = FirebaseAuth.getInstance()

        readdata()
    }

    fun readdata() {
        val user = auth.currentUser
        val email_search = user?.email
        val uid_search = user?.uid

        if (email_search != null && uid_search != null){
            FirebaseDatabase.getInstance().reference
                .child("User_Profile")
                .child(uid_search)
                .addValueEventListener(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError){

                    }

                    override fun onDataChange(p0: DataSnapshot){
                        val map = p0.value as Map<String,Any>
                        txtUserID.text = map["userid"].toString()
                    }
                })
        }
    }

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

        val btnAdd: TextView = root.findViewById(R.id.btnChicken)
        btnAdd.setOnClickListener { view: View ->
            saveCart()
        }

        return root
    }

    private fun displayVegeEgg3Info(it: MenuVegeEgg3Model?){
        Glide.with(requireContext()).load(it!!.image).into(imgPork1)
        txt_pork1!!.text = StringBuilder(it!!.name!!)
        txt_pork1price!!.text = StringBuilder(it!!.price!!.toString())
    }

    private fun initVegeEgg3Views(root: View?) {
        img_pork1 = root!!.findViewById(R.id.imgPork1) as ImageView
        txt_pork1 = root!!.findViewById(R.id.Pork1Name) as TextView
        txt_pork1price = root!!.findViewById(R.id.Pork1Price) as TextView
        userId = root!!.findViewById(R.id.txtUserID) as TextView
    }

    private fun saveCart() {
        val item = txt_pork1!!.text.toString()
        val price = txt_pork1price!!.text.toString()
        val userID = userId!!.text.toString()

        val ref = FirebaseDatabase.getInstance().getReference("Cart")
        val item_id = ref.push().key

        val cart = CartMenuModel(item_id, userID, item, price)

        ref.child(item_id!!).setValue(cart).addOnCompleteListener{
            Toast.makeText(context, "ADD TO CART SUCCESSFULLY!", Toast.LENGTH_SHORT).show()
        }
    }

}