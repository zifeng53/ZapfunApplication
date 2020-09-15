package com.example.zapfunfoodorderingapplication.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zapfunfoodorderingapplication.callback.Pork1MenuLoadCallback
import com.example.zapfunfoodorderingapplication.models.MenuPork1Model
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Pork1MenuViewModel : ViewModel(), Pork1MenuLoadCallback {
    private var pork1ListMutableLiveData: MutableLiveData<List<MenuPork1Model>>?=null
    private var pork1LoadCallbackListener: Pork1MenuLoadCallback = this

    //ERROR MESSAGE
    private lateinit var messageError: MutableLiveData<String>

    //CHICKEN START
    val pork1List: LiveData<List<MenuPork1Model>>
        get(){
            if(pork1ListMutableLiveData == null)
            {
                pork1ListMutableLiveData = MutableLiveData()
                messageError = MutableLiveData()
                loadPork1List()
            }
            return pork1ListMutableLiveData!!
        }
    private fun loadPork1List() {
        val tempList = ArrayList<MenuPork1Model>()
        val pork1Ref = FirebaseDatabase.getInstance().getReference("Pork1")
        pork1Ref.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                for(itemSnapShot in p0!!.children)
                {
                    val model = itemSnapShot.getValue<MenuPork1Model>(MenuPork1Model::class.java)
                    tempList.add(model!!)
                }
                pork1LoadCallbackListener.onPork1LoadSuccess(tempList)
            }

            override fun onCancelled(p0: DatabaseError) {
                pork1LoadCallbackListener.onPork1LoadFailed((p0.message!!))
            }

        })
    }

    override fun onPork1LoadSuccess(pork1ModelListMenu: List<MenuPork1Model>) {
        pork1ListMutableLiveData!!.value = pork1ModelListMenu
    }
    override fun onPork1LoadFailed(message: String) {
        messageError.value = message
    }
}