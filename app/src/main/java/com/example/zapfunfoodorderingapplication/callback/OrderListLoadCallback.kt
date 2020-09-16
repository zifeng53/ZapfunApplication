package com.example.zapfunfoodorderingapplication.callback

import com.example.zapfunfoodorderingapplication.models.CartMenuModel

interface OrderListLoadCallback {
    fun onOrderListLoadSuccess(OrderList:List<CartMenuModel>)
    fun onOrderListLoadFailed(message:String)
}