package com.example.zapfunfoodorderingapplication.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zapfunfoodorderingapplication.callback.MenuDetailLoadCallback
import com.example.zapfunfoodorderingapplication.common.common
import com.example.zapfunfoodorderingapplication.models.MenuDetailModel
import com.example.zapfunfoodorderingapplication.models.MenuTodaySpecialModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MenuDetailViewModel: ViewModel(){

    private var menuDetailListMutableLiveData: MutableLiveData<MenuTodaySpecialModel>?=null

    fun getMenuDetailMutableLiveData():MutableLiveData<MenuTodaySpecialModel>{
        if (menuDetailListMutableLiveData == null)
            menuDetailListMutableLiveData = MutableLiveData()
        menuDetailListMutableLiveData!!.value = common.menuSelected
        return menuDetailListMutableLiveData!!
    }
}