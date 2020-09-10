package com.example.zapfunfoodorderingapplication.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zapfunfoodorderingapplication.Common.Common
import com.example.zapfunfoodorderingapplication.callback.MenuLoadCallback
import com.example.zapfunfoodorderingapplication.models.MenuChickenModel
import com.example.zapfunfoodorderingapplication.models.MenuTodaySpecialModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MyMenuViewModel : ViewModel(), MenuLoadCallback {

    //TODAY SPECIAL
    private var todaySpecialListMutableLiveData:MutableLiveData<List<MenuTodaySpecialModel>>?=null
    private var todaySpecialLoadCallbackListener:MenuLoadCallback = this

    //CHICKEN
    private var chickenListMutableLiveData:MutableLiveData<List<MenuChickenModel>>?=null
    private var chickenLoadCallbackListener:MenuLoadCallback = this

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
        val todaySpecialRef = FirebaseDatabase.getInstance().getReference(Common.TODAY_SPECIAL_REF)
        todaySpecialRef.addListenerForSingleValueEvent(object:ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                for(itemSnapShot in p0!!.children)
                {
                    val model = itemSnapShot.getValue<MenuTodaySpecialModel>(MenuTodaySpecialModel::class.java)
                    tempList.add(model!!)
                }
                todaySpecialLoadCallbackListener.onTodaySpecialLoadSuccess(tempList)
            }

            override fun onCancelled(p0: DatabaseError) {
                todaySpecialLoadCallbackListener.onTodaySpecialLoadFailed((p0.message!!))
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


    //CHICKEN START
    val chickenList:LiveData<List<MenuChickenModel>>
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
        val chickenRef = FirebaseDatabase.getInstance().getReference(Common.CHICKEN_REF)
        chickenRef.addListenerForSingleValueEvent(object:ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                for(itemSnapShot in p0!!.children)
                {
                    val model = itemSnapShot.getValue<MenuChickenModel>(MenuChickenModel::class.java)
                    tempList.add(model!!)
                }
                chickenLoadCallbackListener.onChickenLoadSuccess(tempList)
            }

            override fun onCancelled(p0: DatabaseError) {
                chickenLoadCallbackListener.onTodaySpecialLoadFailed((p0.message!!))
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