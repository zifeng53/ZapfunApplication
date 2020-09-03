package com.example.zapfunfoodorderingapplication

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import kotlinx.android.synthetic.main.activity_my_order.*
import kotlinx.android.synthetic.main.popup_edit_dialog.view.*

class MyOrderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_order)

        textView16.setOnClickListener {
            //Inflate the dialog with custom view
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.popup_edit_dialog, null)
            //AlertDialogBuilder
            val mBuilder = AlertDialog.Builder(this)
                .setView(mDialogView)
                .setTitle("Edit Details")
            //show dialog
            val mAlertDialog = mBuilder.show()
            //cancel button click of custom layout
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
            }
        }

        button12.setOnClickListener {
            if (radio_card.isChecked){
                startActivity(Intent(this, CardCheckoutActivity::class.java))
            }
            else if (radio_cash.isChecked){

            }
        }
    }

    fun checkButton(view: View) {}
}