package com.example.zapfunfoodorderingapplication.utils

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zapfunfoodorderingapplication.common.common
import com.example.zapfunfoodorderingapplication.models.MenuVegeEgg2Model

class VegeEgg2DetailViewModel: ViewModel(){

    private var vegeEgg2DetailListMutableLiveData: MutableLiveData<MenuVegeEgg2Model>?=null

    fun getVegeEgg2DetailMutableLiveData(): MutableLiveData<MenuVegeEgg2Model> {
        if (vegeEgg2DetailListMutableLiveData == null)
            vegeEgg2DetailListMutableLiveData = MutableLiveData()
        vegeEgg2DetailListMutableLiveData!!.value = common.vegeEgg2Selected
        return vegeEgg2DetailListMutableLiveData!!
    }
}