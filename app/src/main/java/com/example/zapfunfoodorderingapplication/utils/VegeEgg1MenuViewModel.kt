package com.example.zapfunfoodorderingapplication.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zapfunfoodorderingapplication.callback.VegeEgg1MenuLoadCallback
import com.example.zapfunfoodorderingapplication.models.MenuVegeEgg1Model
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class VegeEgg1MenuViewModel : ViewModel(), VegeEgg1MenuLoadCallback {
    private var vegeEgg1ListMutableLiveData: MutableLiveData<List<MenuVegeEgg1Model>>?=null
    private var vegeEgg1LoadCallbackListener: VegeEgg1MenuLoadCallback = this

    //ERROR MESSAGE
    private lateinit var messageError: MutableLiveData<String>

    val vegeEgg1List: LiveData<List<MenuVegeEgg1Model>>
        get(){
            if(vegeEgg1ListMutableLiveData == null)
            {
                vegeEgg1ListMutableLiveData = MutableLiveData()
                messageError = MutableLiveData()
                loadVegeEgg1List()
            }
            return vegeEgg1ListMutableLiveData!!
        }
    private fun loadVegeEgg1List() {
        val tempList = ArrayList<MenuVegeEgg1Model>()
        val vegeEgg1Ref = FirebaseDatabase.getInstance().getReference().child("Dishes").child("Vege_Egg1")
        vegeEgg1Ref.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                for(itemSnapShot in p0!!.children)
                {
                    val model = itemSnapShot.getValue<MenuVegeEgg1Model>(MenuVegeEgg1Model::class.java)
                    tempList.add(model!!)
                }
                vegeEgg1LoadCallbackListener.onVegeEgg1LoadSuccess(tempList)
            }

            override fun onCancelled(p0: DatabaseError) {
                vegeEgg1LoadCallbackListener.onVegeEgg1LoadFailed((p0.message!!))
            }

        })
    }

    override fun onVegeEgg1LoadSuccess(vegeEgg1ModelListMenu: List<MenuVegeEgg1Model>) {
        vegeEgg1ListMutableLiveData!!.value = vegeEgg1ModelListMenu
    }
    override fun onVegeEgg1LoadFailed(message: String) {
        messageError.value = message
    }
}