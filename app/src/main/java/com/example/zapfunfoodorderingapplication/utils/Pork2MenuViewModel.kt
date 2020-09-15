package com.example.zapfunfoodorderingapplication.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zapfunfoodorderingapplication.callback.Pork1MenuLoadCallback
import com.example.zapfunfoodorderingapplication.callback.Pork2MenuLoadCallback
import com.example.zapfunfoodorderingapplication.models.MenuPork1Model
import com.example.zapfunfoodorderingapplication.models.MenuPork2Model
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Pork2MenuViewModel : ViewModel(), Pork2MenuLoadCallback {
    private var pork2ListMutableLiveData: MutableLiveData<List<MenuPork2Model>>?=null
    private var pork2LoadCallbackListener: Pork2MenuLoadCallback = this

    //ERROR MESSAGE
    private lateinit var messageError: MutableLiveData<String>

    //CHICKEN START
    val pork2List: LiveData<List<MenuPork2Model>>
        get(){
            if(pork2ListMutableLiveData == null)
            {
                pork2ListMutableLiveData = MutableLiveData()
                messageError = MutableLiveData()
                loadPork2List()
            }
            return pork2ListMutableLiveData!!
        }
    private fun loadPork2List() {
        val tempList = ArrayList<MenuPork2Model>()
        val pork2Ref = FirebaseDatabase.getInstance().getReference().child("Dishes").child("Pork2")
        pork2Ref.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                for(itemSnapShot in p0!!.children)
                {
                    val model = itemSnapShot.getValue<MenuPork2Model>(MenuPork2Model::class.java)
                    tempList.add(model!!)
                }
                pork2LoadCallbackListener.onPork2LoadSuccess(tempList)
            }

            override fun onCancelled(p0: DatabaseError) {
                pork2LoadCallbackListener.onPork2LoadFailed((p0.message!!))
            }

        })
    }

    override fun onPork2LoadSuccess(pork2ModelListMenu: List<MenuPork2Model>) {
        pork2ListMutableLiveData!!.value = pork2ModelListMenu
    }
    override fun onPork2LoadFailed(message: String) {
        messageError.value = message
    }
}