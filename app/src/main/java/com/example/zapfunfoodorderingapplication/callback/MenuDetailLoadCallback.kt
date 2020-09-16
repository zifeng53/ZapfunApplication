package com.example.zapfunfoodorderingapplication.callback

import com.example.zapfunfoodorderingapplication.models.MenuDetailModel

interface MenuDetailLoadCallback {
    fun onMenuDetailLoadSuccess(menuDetailListMenu:List<MenuDetailModel>)
    fun onMenuDetailFailed(message:String)
}