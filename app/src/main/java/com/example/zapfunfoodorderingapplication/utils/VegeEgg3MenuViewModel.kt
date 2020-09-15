package com.example.zapfunfoodorderingapplication.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zapfunfoodorderingapplication.callback.VegeEgg3MenuLoadCallback
import com.example.zapfunfoodorderingapplication.models.MenuVegeEgg3Model
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class VegeEgg3MenuViewModel : ViewModel(), VegeEgg3MenuLoadCallback {
    private var vegeEgg3ListMutableLiveData: MutableLiveData<List<MenuVegeEgg3Model>>?=null
    private var vegeEgg3LoadCallbackListener: VegeEgg3MenuLoadCallback = this

    //ERROR MESSAGE
    private lateinit var messageError: MutableLiveData<String>

    val vegeEgg3List: LiveData<List<MenuVegeEgg3Model>>
        get(){
            if(vegeEgg3ListMutableLiveData == null)
            {
                vegeEgg3ListMutableLiveData = MutableLiveData()
                messageError = MutableLiveData()
                loadVegeEgg3List()
            }
            return vegeEgg3ListMutableLiveData!!
        }
    private fun loadVegeEgg3List() {
        val tempList = ArrayList<MenuVegeEgg3Model>()
        val vegeEgg3Ref = FirebaseDatabase.getInstance().getReference().child("Dishes").child("Vege_Egg3")
        vegeEgg3Ref.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                for(itemSnapShot in p0!!.children)
                {
                    val model = itemSnapShot.getValue<MenuVegeEgg3Model>(MenuVegeEgg3Model::class.java)
                    tempList.add(model!!)
                }
                vegeEgg3LoadCallbackListener.onVegeEgg3LoadSuccess(tempList)
            }

            override fun onCancelled(p0: DatabaseError) {
                vegeEgg3LoadCallbackListener.onVegeEgg3LoadFailed((p0.message!!))
            }

        })
    }

    override fun onVegeEgg3LoadSuccess(vegeEgg3ModelListMenu: List<MenuVegeEgg3Model>) {
        vegeEgg3ListMutableLiveData!!.value = vegeEgg3ModelListMenu
    }
    override fun onVegeEgg3LoadFailed(message: String) {
        messageError.value = message
    }
}