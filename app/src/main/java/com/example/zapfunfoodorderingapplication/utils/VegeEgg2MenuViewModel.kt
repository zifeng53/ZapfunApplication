package com.example.zapfunfoodorderingapplication.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zapfunfoodorderingapplication.callback.VegeEgg2MenuLoadCallback
import com.example.zapfunfoodorderingapplication.models.MenuVegeEgg2Model
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class VegeEgg2MenuViewModel : ViewModel(), VegeEgg2MenuLoadCallback {
    private var vegeEgg2ListMutableLiveData: MutableLiveData<List<MenuVegeEgg2Model>>?=null
    private var vegeEgg2LoadCallbackListener: VegeEgg2MenuLoadCallback = this

    //ERROR MESSAGE
    private lateinit var messageError: MutableLiveData<String>

    val vegeEgg2List: LiveData<List<MenuVegeEgg2Model>>
        get(){
            if(vegeEgg2ListMutableLiveData == null)
            {
                vegeEgg2ListMutableLiveData = MutableLiveData()
                messageError = MutableLiveData()
                loadVegeEgg2List()
            }
            return vegeEgg2ListMutableLiveData!!
        }
    private fun loadVegeEgg2List() {
        val tempList = ArrayList<MenuVegeEgg2Model>()
        val vegeEgg2Ref = FirebaseDatabase.getInstance().getReference().child("Dishes").child("Vege_Egg2")
        vegeEgg2Ref.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                for(itemSnapShot in p0!!.children)
                {
                    val model = itemSnapShot.getValue<MenuVegeEgg2Model>(MenuVegeEgg2Model::class.java)
                    tempList.add(model!!)
                }
                vegeEgg2LoadCallbackListener.onVegeEgg2LoadSuccess(tempList)
            }

            override fun onCancelled(p0: DatabaseError) {
                vegeEgg2LoadCallbackListener.onVegeEgg2LoadFailed((p0.message!!))
            }

        })
    }

    override fun onVegeEgg2LoadSuccess(vegeEgg2ModelListMenu: List<MenuVegeEgg2Model>) {
        vegeEgg2ListMutableLiveData!!.value = vegeEgg2ModelListMenu
    }
    override fun onVegeEgg2LoadFailed(message: String) {
        messageError.value = message
    }
}