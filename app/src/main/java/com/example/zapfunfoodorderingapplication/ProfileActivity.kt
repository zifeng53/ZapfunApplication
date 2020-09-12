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
import kotlinx.android.synthetic.main.activity_profile.*
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

        changeprofilebtn2.setOnClickListener {
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.fragment_my_profile_contact_info_edit, null)

            val mBuilder = AlertDialog.Builder(this)
                .setView(mDialogView)
                .setTitle("Change Contact Info")

            val mAlertDialog = mBuilder.show()

            mDialogView.confirmbtn_contact.setOnClickListener {
                mAlertDialog.dismiss()

                val fname_contact = mDialogView.fname_contact.text.toString()
                val lname_contact = mDialogView.lname_contact.text.toString()
                val phoneno_contact = mDialogView.phoneno_contact.text.toString()
                email_contact.text = email_view2.text.toString()

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