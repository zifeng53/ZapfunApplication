package com.example.zapfunfoodorderingapplication.callback

import com.example.zapfunfoodorderingapplication.models.MenuTodaySpecialModel

interface TodaySpecialMenuLoadCallback {
    fun onTodaySpecialLoadSuccess(todaySpecialModelListMenu:List<MenuTodaySpecialModel>)
    fun onTodaySpecialLoadFailed(message:String)
}