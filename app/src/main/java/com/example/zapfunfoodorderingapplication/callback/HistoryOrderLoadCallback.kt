package com.example.zapfunfoodorderingapplication.callback

import com.example.zapfunfoodorderingapplication.models.OrderModel

interface HistoryOrderLoadCallback {
    fun onHistoryOrderLoadSuccess(HistoryList:List<OrderModel>)
    fun onHistoryOrderLoadFailed(message:String)
}