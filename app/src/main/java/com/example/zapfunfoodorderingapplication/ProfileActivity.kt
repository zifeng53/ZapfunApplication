package com.example.zapfunfoodorderingapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_contact_us.*
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_register_account.*
import kotlinx.android.synthetic.main.activity_register_account.view.*
import kotlinx.android.synthetic.main.contact_info_dialog.*
import kotlinx.android.synthetic.main.contact_info_dialog.view.*
import kotlinx.android.synthetic.main.contact_info_dialog.view.email_contact2
import java.util.stream.DoubleStream.builder

class ProfileActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        auth = FirebaseAuth.getInstance()

        readdata()

        //back to burger menu page
        backbtn_profile2.setOnClickListener {
            val BurgerMenuFragment = BurgerMenuFragment()
            this.supportFragmentManager.beginTransaction().add(R.id.container, BurgerMenuFragment).commit()
        }

        //edit change profile button
        changeprofilebtn2.setOnClickListener {
            //inflate dialog with custom view
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.contact_info_dialog, null)

            //alert dialog builder
            val mBuilder = AlertDialog.Builder(this)
                .setView(mDialogView)
                .setTitle("Change Contact Info")

            //show dialog
            val mAlertDialog = mBuilder.show()

            //confirm button click
            mDialogView.confirmbtn_contact2.setOnClickListener {
                if(mDialogView.fname_contact2.text.toString().isEmpty()) {
                    mDialogView.fname_contact2.error = "Enter first name"
                    mDialogView.fname_contact2.requestFocus()
                    return@setOnClickListener
                }

                if(mDialogView.lname_contact2.text.toString().isEmpty()) {
                    mDialogView.lname_contact2.error = "Enter last name"
                    mDialogView.lname_contact2.requestFocus()
                    return@setOnClickListener
                }

                if(mDialogView.phoneno_contact2.text.toString().isEmpty()) {
                    mDialogView.phoneno_contact2.error = "Enter phone number"
                    mDialogView.phoneno_contact2.requestFocus()
                    return@setOnClickListener
                }

                val user = auth.currentUser
                val user_uid = user?.uid
                val user_email = user?.email
                if (user_email != null && user_uid != null) {
                    FirebaseDatabase.getInstance().reference
                        .child("User_Profile")
                        .child(user_uid)
                        .addValueEventListener(object : ValueEventListener {
                            override fun onCancelled(p0: DatabaseError) {

                            }

                            override fun onDataChange(p0: DataSnapshot) {
                                val map = p0.value as Map<String,Any>
                                mDialogView.email_contact2.text = map["email"].toString()
                            }
                        })
                }

            }

            //cancel button click
            mDialogView.cancelbtn_contact2.setOnClickListener {
                mAlertDialog.dismiss()
            }
        }
    }

    fun readdata() {
        val user = auth.currentUser

        val email_search = user?.email
        val uid_search = user?.uid

        if (email_search != null && uid_search != null) {
            FirebaseDatabase.getInstance().reference
                .child("User_Profile")
                .child(uid_search)
                .addValueEventListener(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {

                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        val map = p0.value as Map<String,Any>
                        email_view2.text = email_search.toString()
                        fname_view3.text = map["first_name"].toString()
                        lname_view2.text = map["last_name"].toString()
                        phone_view2.text = map["phoneno"].toString()
                    }
                })
        }
    }
}