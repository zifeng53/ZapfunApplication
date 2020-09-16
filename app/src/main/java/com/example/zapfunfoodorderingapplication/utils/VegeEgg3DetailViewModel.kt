package com.example.zapfunfoodorderingapplication.utils

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zapfunfoodorderingapplication.common.common
import com.example.zapfunfoodorderingapplication.models.MenuVegeEgg3Model

class VegeEgg3DetailViewModel: ViewModel(){

    private var vegeEgg3DetailListMutableLiveData: MutableLiveData<MenuVegeEgg3Model>?=null

    fun getVegeEgg3DetailMutableLiveData(): MutableLiveData<MenuVegeEgg3Model> {
        if (vegeEgg3DetailListMutableLiveData == null)
            vegeEgg3DetailListMutableLiveData = MutableLiveData()
        vegeEgg3DetailListMutableLiveData!!.value = common.vegeEgg3Selected
        return vegeEgg3DetailListMutableLiveData!!
    }
}