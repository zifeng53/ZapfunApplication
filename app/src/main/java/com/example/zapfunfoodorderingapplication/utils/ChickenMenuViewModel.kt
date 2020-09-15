package com.example.zapfunfoodorderingapplication.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zapfunfoodorderingapplication.callback.ChickenMenuLoadCallback
import com.example.zapfunfoodorderingapplication.models.MenuChickenModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ChickenMenuViewModel : ViewModel(), ChickenMenuLoadCallback {
    //CHICKEN
    private var chickenListMutableLiveData: MutableLiveData<List<MenuChickenModel>>?=null
    private var chickenLoadCallbackListener: ChickenMenuLoadCallback = this

    //ERROR MESSAGE
    private lateinit var messageError:MutableLiveData<String>

    //CHICKEN START
    val chickenList: LiveData<List<MenuChickenModel>>
        get(){
            if(chickenListMutableLiveData == null)
            {
                chickenListMutableLiveData = MutableLiveData()
                messageError = MutableLiveData()
                loadChickenList()
            }
            return chickenListMutableLiveData!!
        }
    private fun loadChickenList() {
        val tempList = ArrayList<MenuChickenModel>()
        val chickenRef = FirebaseDatabase.getInstance().getReference().child("Dishes").child("Chicken")
        chickenRef.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                for(itemSnapShot in p0!!.children)
                {
                    val model = itemSnapShot.getValue<MenuChickenModel>(MenuChickenModel::class.java)
                    tempList.add(model!!)
                }
                chickenLoadCallbackListener.onChickenLoadSuccess(tempList)
            }

            override fun onCancelled(p0: DatabaseError) {
                chickenLoadCallbackListener.onChickenLoadFailed((p0.message!!))
            }

        })
    }

    override fun onChickenLoadSuccess(chickenModelListMenu: List<MenuChickenModel>) {
        chickenListMutableLiveData!!.value = chickenModelListMenu
    }
    override fun onChickenLoadFailed(message: String) {
        messageError.value = message
    }


    //CHICKEN END

}