package com.example.zapfunfoodorderingapplication.callback

import com.example.zapfunfoodorderingapplication.models.CartModel

interface OrderListLoadCallback {
    fun onOrderListLoadSuccess(OrderList:List<CartModel>)
    fun onOrderListLoadFailed(message:String)
}