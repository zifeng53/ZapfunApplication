package com.example.zapfunfoodorderingapplication.utils

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zapfunfoodorderingapplication.common.common
import com.example.zapfunfoodorderingapplication.models.MenuTodaySpecialModel

class TodaySpecialDetailViewModel: ViewModel(){

    private var todaySpecialDetailListMutableLiveData: MutableLiveData<MenuTodaySpecialModel>?=null

    fun getTodaySpecialDetailMutableLiveData():MutableLiveData<MenuTodaySpecialModel>{
        if (todaySpecialDetailListMutableLiveData == null)
            todaySpecialDetailListMutableLiveData = MutableLiveData()
        todaySpecialDetailListMutableLiveData!!.value = common.todaySpecialSelected
        return todaySpecialDetailListMutableLiveData!!
    }
}