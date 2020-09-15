package com.example.zapfunfoodorderingapplication.callback

import com.example.zapfunfoodorderingapplication.models.MenuPork2Model

interface Pork2MenuLoadCallback {
    fun onPork2LoadSuccess(pork2ModelListMenu:List<MenuPork2Model>)
    fun onPork2LoadFailed(message:String)
}