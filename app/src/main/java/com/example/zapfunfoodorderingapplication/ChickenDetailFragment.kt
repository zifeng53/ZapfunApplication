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
import com.example.zapfunfoodorderingapplication.models.MenuChickenModel
import com.example.zapfunfoodorderingapplication.utils.ChickenDetailViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_chicken_detail.*
import kotlinx.android.synthetic.main.fragment_chicken_detail.txtUserID
import kotlinx.android.synthetic.main.fragment_today_special_detail.*

class ChickenDetailFragment : Fragment() {

    private lateinit var chickenDetailViewModel: ChickenDetailViewModel

    private var img_chicken:ImageView?=null
    private var txt_chicken:TextView?=null
    private var txt_chickenprice:TextView?=null
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
        chickenDetailViewModel = ViewModelProviders.of(this).get(ChickenDetailViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_chicken_detail, container, false)

        val btnBack: TextView = root.findViewById(R.id.txtBack)
        btnBack.setOnClickListener{view : View ->
            view.findNavController().navigate(R.id.action_chickenDetailFragment_to_myMenuFragment)}

        initChickenViews(root)

        chickenDetailViewModel.getChickenDetailMutableLiveData().observe(viewLifecycleOwner, Observer {
            displayChickenInfo(it)
        })

        val btnAdd: TextView = root.findViewById(R.id.btnChicken)
        btnAdd.setOnClickListener { view: View ->
            saveCart()
        }

        return root
    }

    private fun displayChickenInfo(it: MenuChickenModel?){
        Glide.with(requireContext()).load(it!!.image).into(imgChicken)
        txt_chicken!!.text = StringBuilder(it!!.name!!)
        txt_chickenprice!!.text = StringBuilder(it!!.price!!.toString())
    }

    private fun initChickenViews(root: View?) {
        img_chicken = root!!.findViewById(R.id.imgChicken) as ImageView
        txt_chicken = root!!.findViewById(R.id.ChickenName) as TextView
        txt_chickenprice = root!!.findViewById(R.id.ChickenPrice) as TextView
        userId = root!!.findViewById(R.id.txtUserID) as TextView
    }

    private fun saveCart() {
        val item = txt_chicken!!.text.toString()
        val price = txt_chickenprice!!.text.toString()
        val userID = userId!!.text.toString()

        val ref = FirebaseDatabase.getInstance().getReference("Cart")
        val item_id = ref.push().key

        val cart = CartMenuModel(item_id, userID, item, price)

        ref.child(item_id!!).setValue(cart).addOnCompleteListener{
            Toast.makeText(context, "ADD TO CART SUCCESSFULLY!", Toast.LENGTH_SHORT).show()
        }
    }

}