package com.example.zapfunfoodorderingapplication.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zapfunfoodorderingapplication.callback.VegeEgg4MenuLoadCallback
import com.example.zapfunfoodorderingapplication.models.MenuVegeEgg4Model
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class VegeEgg4MenuViewModel : ViewModel(), VegeEgg4MenuLoadCallback {
    private var vegeEgg4ListMutableLiveData: MutableLiveData<List<MenuVegeEgg4Model>>?=null
    private var vegeEgg4LoadCallbackListener: VegeEgg4MenuLoadCallback = this

    //ERROR MESSAGE
    private lateinit var messageError: MutableLiveData<String>

    val vegeEgg4List: LiveData<List<MenuVegeEgg4Model>>
        get(){
            if(vegeEgg4ListMutableLiveData == null)
            {
                vegeEgg4ListMutableLiveData = MutableLiveData()
                messageError = MutableLiveData()
                loadVegeEgg4List()
            }
            return vegeEgg4ListMutableLiveData!!
        }
    private fun loadVegeEgg4List() {
        val tempList = ArrayList<MenuVegeEgg4Model>()
        val vegeEgg4Ref = FirebaseDatabase.getInstance().getReference().child("Dishes").child("Vege_Egg4")
        vegeEgg4Ref.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                for(itemSnapShot in p0!!.children)
                {
                    val model = itemSnapShot.getValue<MenuVegeEgg4Model>(MenuVegeEgg4Model::class.java)
                    tempList.add(model!!)
                }
                vegeEgg4LoadCallbackListener.onVegeEgg4LoadSuccess(tempList)
            }

            override fun onCancelled(p0: DatabaseError) {
                vegeEgg4LoadCallbackListener.onVegeEgg4LoadFailed((p0.message!!))
            }

        })
    }

    override fun onVegeEgg4LoadSuccess(vegeEgg4ModelListMenu: List<MenuVegeEgg4Model>) {
        vegeEgg4ListMutableLiveData!!.value = vegeEgg4ModelListMenu
    }
    override fun onVegeEgg4LoadFailed(message: String) {
        messageError.value = message
    }
}