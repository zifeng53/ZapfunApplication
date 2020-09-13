package com.example.zapfunfoodorderingapplication.callback

import com.example.zapfunfoodorderingapplication.models.MenuPork1Model


interface Pork1MenuLoadCallback {
    fun onPork1LoadSuccess(pork1ModelListMenu:List<MenuPork1Model>)
    fun onPork1LoadFailed(message:String)
}