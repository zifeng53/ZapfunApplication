package com.example.zapfunfoodorderingapplication.callback

import com.example.zapfunfoodorderingapplication.models.MenuVegeEgg1Model


interface VegeEgg1MenuLoadCallback {
    fun onVegeEgg1LoadSuccess(vegeEgg1ModelListMenu:List<MenuVegeEgg1Model>)
    fun onVegeEgg1LoadFailed(message:String)
}