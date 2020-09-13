package com.example.zapfunfoodorderingapplication

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zapfunfoodorderingapplication.adapters.OrderItemHeaderRecyclerAdapter
import com.example.zapfunfoodorderingapplication.utils.JsonHelper2
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_my_order.*
import kotlinx.android.synthetic.main.popup_edit_dialog.view.*

class MyOrderFragment : Fragment() {

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
                        textView32.text = map["last_name"].toString()
                        textView33.text = map["phoneno"].toString()
                        textView34.text = map["address"].toString()
                        textView35.text = map["floor"].toString()
                    }
                })
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_my_order, container, false)
        val btnEdit: TextView = view.findViewById(R.id.textView16)
        btnEdit.setOnClickListener{view : View ->
            //Inflate the dialog with custom view
            val mDialogView = LayoutInflater.from(activity).inflate(R.layout.popup_edit_dialog, null)
            //AlertDialogBuilder
            val mBuilder = AlertDialog.Builder(activity)
                .setView(mDialogView)
                .setTitle("Edit Details")
            //show dialog
            val mAlertDialog = mBuilder.show()

            mDialogView.editTextTextPersonName.setText(textView32.text)
            mDialogView.editTextTextPersonName2.setText(textView33.text)
            mDialogView.editTextTextPersonName3.setText(textView34.text)
            mDialogView.editTextTextPersonName4.setText(textView35.text)
            mDialogView.editTextTextPersonName5.setText(textView36.text)

            mDialogView.button2.setOnClickListener {
                mAlertDialog.dismiss()
                //get text from EditTexts of custom layout
                val name = mDialogView.editTextTextPersonName.text.toString()
                val phone = mDialogView.editTextTextPersonName2.text.toString()
                val street = mDialogView.editTextTextPersonName3.text.toString()
                val unit = mDialogView.editTextTextPersonName4.text.toString()
                val remark = mDialogView.editTextTextPersonName5.text.toString()
                //set the input text in TextView of MyOrder page
                textView32.setText(name)
                textView33.setText(phone)
                textView34.setText(street)
                textView35.setText(unit)
                textView36.setText(remark)
            }
            //cancel button click of custom layout
            mDialogView.button.setOnClickListener {
                //dismiss dialog
                mAlertDialog.dismiss()
            }}
        val btnCheckout: Button = view.findViewById(R.id.button12)
        btnCheckout.setOnClickListener{view : View ->
            if (radio_card.isChecked){
                view.findNavController().navigate(R.id.action_myOrderFragment_to_cardCheckoutActivity)
            }
            else if (radio_cash.isChecked){

            }
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        recyclerOrderList.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = OrderItemHeaderRecyclerAdapter((this.context!!), JsonHelper2(this.context!!).getListData())
        }
    }
    companion object{
        fun newInstance(): MyOrderFragment = MyOrderFragment()
    }
}