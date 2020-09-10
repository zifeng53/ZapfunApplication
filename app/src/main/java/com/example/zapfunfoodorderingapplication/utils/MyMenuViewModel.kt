package com.example.zapfunfoodorderingapplication.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zapfunfoodorderingapplication.Common.Common
import com.example.zapfunfoodorderingapplication.callback.IPopularLoadCallback
import com.example.zapfunfoodorderingapplication.models.TodaySpecialModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MyMenuViewModel : ViewModel(), IPopularLoadCallback {

    private var popularListMutableLiveData:MutableLiveData<List<TodaySpecialModel>>?=null
    private lateinit var messageError:MutableLiveData<String>
    private var popularLoadCallbackListener:IPopularLoadCallback = this

    val popularList:LiveData<List<TodaySpecialModel>>
        get(){
            if(popularListMutableLiveData == null)
            {
                popularListMutableLiveData = MutableLiveData()
                messageError = MutableLiveData()
                loadTodaySpecialList()
            }
            return popularListMutableLiveData!!
        }

    private fun loadTodaySpecialList() {
        val tempList = ArrayList<TodaySpecialModel>()
        val popularRef = FirebaseDatabase.getInstance().getReference(Common.TODAY_SPECIAL_REF)
        popularRef.addListenerForSingleValueEvent(object:ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                for(itemSnapShot in p0!!.children)
                {
                    val model = itemSnapShot.getValue<TodaySpecialModel>(TodaySpecialModel::class.java)
                    tempList.add(model!!)
                }
                popularLoadCallbackListener.onTodaySpecialLoadSuccess(tempList)
            }

            override fun onCancelled(p0: DatabaseError) {
                popularLoadCallbackListener.onTodaySpecialLoadFailed((p0.message!!))
            }

        })
    }

    override fun onTodaySpecialLoadSuccess(popularModelList: List<TodaySpecialModel>) {
        popularListMutableLiveData!!.value = popularModelList
    }

    override fun onTodaySpecialLoadFailed(message: String) {
        messageError.value = message
    }
}