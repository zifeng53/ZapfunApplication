package com.example.zapfunfoodorderingapplication.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zapfunfoodorderingapplication.callback.OrderListLoadCallback
import com.example.zapfunfoodorderingapplication.models.CartMenuModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class CartOrderViewModel: ViewModel(),OrderListLoadCallback {
    private var orderListMutableLiveData: MutableLiveData<List<CartMenuModel>>?=null
    private var orderLoadCallbackListener: OrderListLoadCallback = this

    private lateinit var auth: FirebaseAuth

    private lateinit var messageError: MutableLiveData<String>

    val cartList: LiveData<List<CartMenuModel>>
    get(){
        if(orderListMutableLiveData == null)
        {
            orderListMutableLiveData = MutableLiveData()
            messageError = MutableLiveData()
            loadCartList()
        }
        return orderListMutableLiveData!!
    }

    private fun loadCartList(){
        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        val uidSearch = user?.uid

        val tempList = ArrayList<CartMenuModel>()
        val cartRef = FirebaseDatabase.getInstance().getReference().child("Cart")
            .orderByChild("user_id").equalTo(uidSearch)
            cartRef.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(p0: DataSnapshot){
                for(itemSnapShot in p0.children)
                {
                    val model = itemSnapShot.getValue<CartMenuModel>(CartMenuModel::class.java)
                    tempList.add(model!!)
                }
                orderLoadCallbackListener.onOrderListLoadSuccess(tempList)
            }
                override fun onCancelled(p0: DatabaseError){
                    orderLoadCallbackListener.onOrderListLoadFailed((p0.message!!))
                }
        })
    }

    override fun onOrderListLoadSuccess(OrderList: List<CartMenuModel>) {
        orderListMutableLiveData!!.value = OrderList
    }

    override fun onOrderListLoadFailed(message: String) {
        messageError.value = message
    }
}