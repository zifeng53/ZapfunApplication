package com.example.zapfunfoodorderingapplication.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zapfunfoodorderingapplication.callback.OrderListLoadCallback
import com.example.zapfunfoodorderingapplication.models.CartModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class CartOrderViewModel: ViewModel(),OrderListLoadCallback {
    private var orderListMutableLiveData: MutableLiveData<List<CartModel>>?=null
    private var orderLoadCallbackListener: OrderListLoadCallback = this

    private lateinit var auth: FirebaseAuth

    private lateinit var messageError: MutableLiveData<String>

    val cartList: LiveData<List<CartModel>>
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

        val tempList = ArrayList<CartModel>()
        val cartRef = FirebaseDatabase.getInstance().getReference().child("Cart2")
            .orderByChild("user_id").equalTo(uidSearch)
            cartRef.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(p0: DataSnapshot){
                for(itemSnapShot in p0.children)
                {
                    val model = itemSnapShot.getValue<CartModel>(CartModel::class.java)
                    tempList.add(model!!)
                }
                //tempList.sortByDescending { selector(it) }
                orderLoadCallbackListener.onOrderListLoadSuccess(tempList)
            }
                override fun onCancelled(p0: DatabaseError){
                    orderLoadCallbackListener.onOrderListLoadFailed((p0.message!!))
                }
        })
    }

    fun selector(p: CartModel): String? = p.user_id
    override fun onOrderListLoadSuccess(OrderList: List<CartModel>) {
        orderListMutableLiveData!!.value = OrderList
    }

    override fun onOrderListLoadFailed(message: String) {
        messageError.value = message
    }
}