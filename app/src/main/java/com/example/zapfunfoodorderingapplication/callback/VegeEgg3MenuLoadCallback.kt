package com.example.zapfunfoodorderingapplication.callback

import com.example.zapfunfoodorderingapplication.models.MenuVegeEgg3Model

interface VegeEgg3MenuLoadCallback {
    fun onVegeEgg3LoadSuccess(vegeEgg3ModelListMenu:List<MenuVegeEgg3Model>)
    fun onVegeEgg3LoadFailed(message:String)
}