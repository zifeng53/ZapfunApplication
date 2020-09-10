package com.example.zapfunfoodorderingapplication.callback

import com.example.zapfunfoodorderingapplication.models.TodaySpecialModel

interface IPopularLoadCallback {
    fun onTodaySpecialLoadSuccess(popularModelList:List<TodaySpecialModel>)
    fun onTodaySpecialLoadFailed(message:String)
}