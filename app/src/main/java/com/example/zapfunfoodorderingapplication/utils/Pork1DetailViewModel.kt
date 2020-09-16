package com.example.zapfunfoodorderingapplication.utils

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zapfunfoodorderingapplication.common.common
import com.example.zapfunfoodorderingapplication.models.MenuPork1Model

class Pork1DetailViewModel: ViewModel(){

    private var pork1DetailListMutableLiveData: MutableLiveData<MenuPork1Model>?=null

    fun getPork1DetailMutableLiveData(): MutableLiveData<MenuPork1Model> {
        if (pork1DetailListMutableLiveData == null)
            pork1DetailListMutableLiveData = MutableLiveData()
        pork1DetailListMutableLiveData!!.value = common.pork1Selected
        return pork1DetailListMutableLiveData!!
    }
}