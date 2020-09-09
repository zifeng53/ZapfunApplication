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
import kotlinx.android.synthetic.main.fragment_my_order.*
import kotlinx.android.synthetic.main.popup_edit_dialog.view.*

class MyOrderFragment : Fragment() {

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
            mDialogView.button2.setOnClickListener {
                mAlertDialog.dismiss()
                //get text from EditTexts of custom layout
                //val name = mDialogView.dialogNameEt.text.toString()
                //val email = mDialogView.dialogEmailEt.text.toString()
                //val password = mDialogView.dialogPasswEt.text.toString()
                //set the input text in TextView
                //mainInfoTv.setText("Name:"+ name +"\nEmail: "+ email +"\nPassword: "+ password)
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
}