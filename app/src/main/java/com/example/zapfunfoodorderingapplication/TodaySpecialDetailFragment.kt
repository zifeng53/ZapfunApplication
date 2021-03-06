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
import com.example.zapfunfoodorderingapplication.models.*
import com.example.zapfunfoodorderingapplication.utils.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_my_menu.*
import kotlinx.android.synthetic.main.fragment_today_special_detail.*
import kotlinx.android.synthetic.main.fragment_today_special_detail.txtUserID

class TodaySpecialDetailFragment : Fragment() {

    private lateinit var todaySpecialDetailViewModel: TodaySpecialDetailViewModel

    private var img_menu: ImageView? = null
    private var txt_name: TextView? = null
    private var txt_price: TextView? = null
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
        todaySpecialDetailViewModel =
            ViewModelProviders.of(this).get(TodaySpecialDetailViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_today_special_detail, container, false)

        val btnBack: TextView = root.findViewById(R.id.txtBack)
        btnBack.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(R.id.action_todaySpecialDetailFragment_to_myMenuFragment)
        }

        initViews(root)

        todaySpecialDetailViewModel.getTodaySpecialDetailMutableLiveData()
            .observe(viewLifecycleOwner, Observer {
                displayTodaySpecialInfo(it)
            })

        val btnAdd: TextView = root.findViewById(R.id.btnTodaySpecial)
        btnAdd.setOnClickListener { view: View ->
            saveCart()
        }

        return root
    }

    private fun displayTodaySpecialInfo(it: MenuTodaySpecialModel?) {
        Glide.with(requireContext()).load(it!!.image).into(imgTodaySpecial)
        txt_name!!.text = StringBuilder(it!!.name!!)
        txt_price!!.text = StringBuilder(it!!.price!!.toString())
    }

    private fun initViews(root: View?) {
        img_menu = root!!.findViewById(R.id.imgTodaySpecial) as ImageView
        txt_name = root!!.findViewById(R.id.TodaySpecialName) as TextView
        txt_price = root!!.findViewById(R.id.TodaySpecialPrice) as TextView
        userId = root!!.findViewById(R.id.txtUserID) as TextView
    }

    private fun saveCart() {
        val item = txt_name!!.text.toString()
        val price = txt_price!!.text.toString()
        val intPrice = price.toDouble()
        val userID = userId!!.text.toString()

        val ref = FirebaseDatabase.getInstance().getReference("Cart")
        val item_id = ref.push().key

        val cart = CartMenuModel(item_id, userID, item, intPrice)

        ref.child(item_id!!).setValue(cart).addOnCompleteListener{
            Toast.makeText(context, "ADD TO CART SUCCESSFULLY!", Toast.LENGTH_SHORT).show()
            requireView().findNavController()
                .navigate(R.id.action_todaySpecialDetailFragment_to_myMenuFragment)
        }
    }
}