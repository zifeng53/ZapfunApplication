package com.example.zapfunfoodorderingapplication.callback

import com.example.zapfunfoodorderingapplication.models.MenuFishModel


interface FishMenuLoadCallback {
    fun onFishLoadSuccess(fishModelListMenu:List<MenuFishModel>)
    fun onFishLoadFailed(message:String)
}