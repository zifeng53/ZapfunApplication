package com.example.zapfunfoodorderingapplication.utils

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zapfunfoodorderingapplication.common.common
import com.example.zapfunfoodorderingapplication.models.MenuFishModel

class FishDetailViewModel: ViewModel(){

    private var fishDetailListMutableLiveData: MutableLiveData<MenuFishModel>?=null

    fun getFishDetailMutableLiveData(): MutableLiveData<MenuFishModel> {
        if (fishDetailListMutableLiveData == null)
            fishDetailListMutableLiveData = MutableLiveData()
        fishDetailListMutableLiveData!!.value = common.fishSelected
        return fishDetailListMutableLiveData!!
    }
}