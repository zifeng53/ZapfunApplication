package com.example.zapfunfoodorderingapplication.utils

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zapfunfoodorderingapplication.common.common
import com.example.zapfunfoodorderingapplication.models.MenuPork2Model

class Pork2DetailViewModel: ViewModel(){

    private var pork2DetailListMutableLiveData: MutableLiveData<MenuPork2Model>?=null

    fun getPork2DetailMutableLiveData(): MutableLiveData<MenuPork2Model> {
        if (pork2DetailListMutableLiveData == null)
            pork2DetailListMutableLiveData = MutableLiveData()
        pork2DetailListMutableLiveData!!.value = common.pork2Selected
        return pork2DetailListMutableLiveData!!
    }
}