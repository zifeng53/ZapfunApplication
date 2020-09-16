package com.example.zapfunfoodorderingapplication.utils

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zapfunfoodorderingapplication.common.common
import com.example.zapfunfoodorderingapplication.models.MenuChickenModel

class ChickenDetailViewModel: ViewModel(){

    private var chickenDetailListMutableLiveData: MutableLiveData<MenuChickenModel>?=null

    fun getChickenDetailMutableLiveData(): MutableLiveData<MenuChickenModel> {
        if (chickenDetailListMutableLiveData == null)
            chickenDetailListMutableLiveData = MutableLiveData()
        chickenDetailListMutableLiveData!!.value = common.chickenSelected
        return chickenDetailListMutableLiveData!!
    }
}