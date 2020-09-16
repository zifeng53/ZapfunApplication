package com.example.zapfunfoodorderingapplication.utils

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zapfunfoodorderingapplication.common.common
import com.example.zapfunfoodorderingapplication.models.MenuVegeEgg4Model

class VegeEgg4DetailViewModel: ViewModel(){

    private var vegeEgg4DetailListMutableLiveData: MutableLiveData<MenuVegeEgg4Model>?=null

    fun getVegeEgg4DetailMutableLiveData(): MutableLiveData<MenuVegeEgg4Model> {
        if (vegeEgg4DetailListMutableLiveData == null)
            vegeEgg4DetailListMutableLiveData = MutableLiveData()
        vegeEgg4DetailListMutableLiveData!!.value = common.vegeEgg4Selected
        return vegeEgg4DetailListMutableLiveData!!
    }
}