package com.example.zapfunfoodorderingapplication.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zapfunfoodorderingapplication.callback.HistoryOrderLoadCallback
import com.example.zapfunfoodorderingapplication.models.OrderModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HistoryOrderViewModel: ViewModel(),HistoryOrderLoadCallback {

    //HISTORY
    private var historyListMutableLiveData: MutableLiveData<List<OrderModel>>?=null
    private var historyLoadCallbackListener: HistoryOrderLoadCallback = this

    private lateinit var auth: FirebaseAuth

    //ERROR MESSAGE
    private lateinit var messageError: MutableLiveData<String>

    //HISTORY START
    val historyList: LiveData<List<OrderModel>>
        get(){
            if(historyListMutableLiveData == null)
            {
                historyListMutableLiveData = MutableLiveData()
                messageError = MutableLiveData()
                loadHistoryList()
            }
            return historyListMutableLiveData!!
        }
    private fun loadHistoryList() {
        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        val uidSearch = user?.uid

        val tempList = ArrayList<OrderModel>()
        val historyRef = FirebaseDatabase.getInstance().getReference().child("Order")
            .orderByChild("user_id").equalTo(uidSearch)
        historyRef.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                for(itemSnapShot in p0.children)
                {
                        val model = itemSnapShot.getValue<OrderModel>(OrderModel::class.java)
                        tempList.add(model!!)

                }
                tempList.sortByDescending { selector(it) }
                historyLoadCallbackListener.onHistoryOrderLoadSuccess(tempList)
            }

            override fun onCancelled(p0: DatabaseError) {
                historyLoadCallbackListener.onHistoryOrderLoadFailed((p0.message!!))
            }
        })
    }
    fun selector(o: OrderModel): String? = o.order_status
    override fun onHistoryOrderLoadSuccess(HistoryList: List<OrderModel>) {
        historyListMutableLiveData!!.value = HistoryList
    }

    override fun onHistoryOrderLoadFailed(message: String) {
        messageError.value = message
    }
    //HISTORY END
}