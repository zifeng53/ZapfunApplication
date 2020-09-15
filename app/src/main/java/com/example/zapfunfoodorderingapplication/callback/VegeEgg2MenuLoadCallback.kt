package com.example.zapfunfoodorderingapplication.callback

import com.example.zapfunfoodorderingapplication.models.MenuVegeEgg2Model

interface VegeEgg2MenuLoadCallback {
    fun onVegeEgg2LoadSuccess(vegeEgg2ModelListMenu:List<MenuVegeEgg2Model>)
    fun onVegeEgg2LoadFailed(message:String)
}