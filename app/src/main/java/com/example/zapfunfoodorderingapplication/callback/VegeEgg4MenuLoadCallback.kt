package com.example.zapfunfoodorderingapplication.callback

import com.example.zapfunfoodorderingapplication.models.MenuVegeEgg4Model

interface VegeEgg4MenuLoadCallback {
    fun onVegeEgg4LoadSuccess(vegeEgg4ModelListMenu:List<MenuVegeEgg4Model>)
    fun onVegeEgg4LoadFailed(message:String)
}