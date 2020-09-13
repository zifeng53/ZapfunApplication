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
import kotlinx.android.synthetic.main.fragment_my_profile_change_password_edit.*
import kotlinx.android.synthetic.main.fragment_my_profile_change_password_edit.view.*
import kotlinx.android.synthetic.main.fragment_my_profile_change_password_edit.view.newpassword_contact
import kotlinx.android.synthetic.main.fragment_my_profile_contact_info_edit.*
import kotlinx.android.synthetic.main.fragment_my_profile_contact_info_edit.view.*
import kotlinx.android.synthetic.main.fragment_my_profile_contact_info_edit.view.email_contact
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

        //edit change password button
        changepasswordbtn2.setOnClickListener {
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.fragment_my_profile_change_password_edit, null)

            val mBuilder = AlertDialog.Builder(this)
                .setView(mDialogView)
                .setTitle("Change Password")

            val mAlertDialog = mBuilder.show()

            mDialogView.confirmbtn_password.setOnClickListener {
                mAlertDialog.dismiss()

                if(mDialogView.currentpassword_contact.text.toString().isEmpty()) {
                    mDialogView.currentpassword_contact.error = "Enter current password"
                    mDialogView.currentpassword_contact.requestFocus()
                    return@setOnClickListener
                }

                if(mDialogView.newpassword_contact.text.toString().isEmpty()) {
                    mDialogView.newpassword_contact.error = "Enter new password"
                    mDialogView.newpassword_contact.requestFocus()
                    return@setOnClickListener
                }

                if(mDialogView.confirmpassword_contact.text.toString().isEmpty()) {
                    mDialogView.confirmpassword_contact.error = "Enter confirm password"
                    mDialogView.confirmpassword_contact.requestFocus()
                    return@setOnClickListener
                }

                if(!mDialogView.newpassword_contact.text.toString().equals(mDialogView.confirmpassword_contact.text.toString())) {
                    mDialogView.confirmpassword_contact.error = "Those passwords didnt match"
                    mDialogView.confirmpassword_contact.requestFocus()
                    return@setOnClickListener
                }
            }
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
            mDialogView.confirmbtn_contact.setOnClickListener {

                val fname_contact = mDialogView.fname_contact.text.toString()
                val lname_contact = mDialogView.lname_contact.text.toString()
                val phoneno_contact = mDialogView.phoneno_contact.text.toString()
                email_contact.text = email_view2.text.toString()


                if(fname_contact.isEmpty()) {
                    mDialogView.fname_contact.error = "Enter first name"
                    mDialogView.fname_contact.requestFocus()
                    return@setOnClickListener
                }

                if(mDialogView.fname_contact.text.toString().isNotEmpty() &&
                    mDialogView.lname_contact.text.toString().isNotEmpty() &&
                    mDialogView.phoneno_contact.text.toString().isNotEmpty()) {
                    //dismiss dialog
                    mAlertDialog.dismiss()
                }

            }

            mDialogView.cancelbtn_contact.setOnClickListener {
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