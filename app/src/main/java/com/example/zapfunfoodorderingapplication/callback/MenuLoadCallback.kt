package com.example.zapfunfoodorderingapplication.callback

import com.example.zapfunfoodorderingapplication.models.MenuChickenModel
import com.example.zapfunfoodorderingapplication.models.MenuTodaySpecialModel

interface MenuLoadCallback {
    fun onTodaySpecialLoadSuccess(todaySpecialModelListMenu:List<MenuTodaySpecialModel>)
    fun onTodaySpecialLoadFailed(message:String)
    //fun onChickenLoadSuccess(chickenModelListMenu:List<MenuChickenModel>)
    //fun onChickenLoadFailed(message:String)
}