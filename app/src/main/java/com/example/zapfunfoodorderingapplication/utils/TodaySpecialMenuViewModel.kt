package com.example.zapfunfoodorderingapplication.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zapfunfoodorderingapplication.callback.TodaySpecialMenuLoadCallback
import com.example.zapfunfoodorderingapplication.models.MenuTodaySpecialModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class TodaySpecialMenuViewModel : ViewModel(), TodaySpecialMenuLoadCallback {

    //TODAY SPECIAL
    private var todaySpecialListMutableLiveData:MutableLiveData<List<MenuTodaySpecialModel>>?=null
    private var todaySpecialLoadCallbackListenerTodaySpecial:TodaySpecialMenuLoadCallback = this

    //ERROR MESSAGE
    private lateinit var messageError:MutableLiveData<String>

    //TODAY SPECIAL START
    val todaySpecialList:LiveData<List<MenuTodaySpecialModel>>
        get(){
            if(todaySpecialListMutableLiveData == null)
            {
                todaySpecialListMutableLiveData = MutableLiveData()
                messageError = MutableLiveData()
                loadTodaySpecialList()
            }
            return todaySpecialListMutableLiveData!!
        }
    private fun loadTodaySpecialList() {
        val tempList = ArrayList<MenuTodaySpecialModel>()
        val todaySpecialRef = FirebaseDatabase.getInstance().getReference().child("Dishes").child("Today_Special")
        todaySpecialRef.addListenerForSingleValueEvent(object:ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                for(itemSnapShot in p0!!.children)
                {
                    val model = itemSnapShot.getValue<MenuTodaySpecialModel>(MenuTodaySpecialModel::class.java)
                    tempList.add(model!!)
                }
                todaySpecialLoadCallbackListenerTodaySpecial.onTodaySpecialLoadSuccess(tempList)
            }

            override fun onCancelled(p0: DatabaseError) {
                todaySpecialLoadCallbackListenerTodaySpecial.onTodaySpecialLoadFailed((p0.message!!))
            }

        })
    }
    override fun onTodaySpecialLoadSuccess(todaySpecialModelListMenu: List<MenuTodaySpecialModel>) {
        todaySpecialListMutableLiveData!!.value = todaySpecialModelListMenu
    }
    override fun onTodaySpecialLoadFailed(message: String) {
        messageError.value = message
    }
    //TODAY SPECIAL END
}