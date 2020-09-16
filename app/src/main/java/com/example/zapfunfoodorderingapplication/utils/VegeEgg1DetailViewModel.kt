package com.example.zapfunfoodorderingapplication.utils

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zapfunfoodorderingapplication.common.common
import com.example.zapfunfoodorderingapplication.models.MenuVegeEgg1Model

class VegeEgg1DetailViewModel: ViewModel(){

    private var vegeEgg1DetailListMutableLiveData: MutableLiveData<MenuVegeEgg1Model>?=null

    fun getVegeEgg1DetailMutableLiveData(): MutableLiveData<MenuVegeEgg1Model> {
        if (vegeEgg1DetailListMutableLiveData == null)
            vegeEgg1DetailListMutableLiveData = MutableLiveData()
        vegeEgg1DetailListMutableLiveData!!.value = common.vegeEgg1Selected
        return vegeEgg1DetailListMutableLiveData!!
    }
}