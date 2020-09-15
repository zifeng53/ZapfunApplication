package com.example.zapfunfoodorderingapplication.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zapfunfoodorderingapplication.callback.FishMenuLoadCallback
import com.example.zapfunfoodorderingapplication.models.MenuFishModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FishMenuViewModel : ViewModel(), FishMenuLoadCallback {
    private var fishListMutableLiveData: MutableLiveData<List<MenuFishModel>>?=null
    private var fishLoadCallbackListener: FishMenuLoadCallback = this

    //ERROR MESSAGE
    private lateinit var messageError: MutableLiveData<String>

    val fishList: LiveData<List<MenuFishModel>>
        get(){
            if(fishListMutableLiveData == null)
            {
                fishListMutableLiveData = MutableLiveData()
                messageError = MutableLiveData()
                loadFishList()
            }
            return fishListMutableLiveData!!
        }
    private fun loadFishList() {
        val tempList = ArrayList<MenuFishModel>()
        val fishRef = FirebaseDatabase.getInstance().getReference().child("Dishes").child("Fish")
        fishRef.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                for(itemSnapShot in p0!!.children)
                {
                    val model = itemSnapShot.getValue<MenuFishModel>(MenuFishModel::class.java)
                    tempList.add(model!!)
                }
                fishLoadCallbackListener.onFishLoadSuccess(tempList)
            }

            override fun onCancelled(p0: DatabaseError) {
                fishLoadCallbackListener.onFishLoadFailed((p0.message!!))
            }

        })
    }

    override fun onFishLoadSuccess(fishModelListMenu: List<MenuFishModel>) {
        fishListMutableLiveData!!.value = fishModelListMenu
    }
    override fun onFishLoadFailed(message: String) {
        messageError.value = message
    }
}