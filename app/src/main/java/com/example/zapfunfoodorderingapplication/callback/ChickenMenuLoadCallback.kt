package com.example.zapfunfoodorderingapplication.callback

import com.example.zapfunfoodorderingapplication.models.MenuChickenModel

interface ChickenMenuLoadCallback {
    fun onChickenLoadSuccess(chickenModelListMenu:List<MenuChickenModel>)
    fun onChickenLoadFailed(message:String)
}