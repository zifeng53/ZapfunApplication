package com.example.zapfunfoodorderingapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.zapfunfoodorderingapplication.R
import com.example.zapfunfoodorderingapplication.callback.MenuClickListener
import com.example.zapfunfoodorderingapplication.common.common
import com.example.zapfunfoodorderingapplication.models.MenuVegeEgg3Model
import com.squareup.picasso.Picasso

class VegeEgg3Adapter(private val context: Context,
                      private val VegeEgg3List:List<MenuVegeEgg3Model>?)
    : RecyclerView.Adapter<VegeEgg3Adapter.VegeEgg3Holder>() {

    inner class VegeEgg3Holder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {

        var vegeEgg3_name: TextView
        var vegeEgg3_img: ImageView

        lateinit var menuClickListener: MenuClickListener

        fun setClick(menuClickListener: MenuClickListener) {
            this.menuClickListener = menuClickListener
        }

        init {
            vegeEgg3_name = view.findViewById(R.id.vege_egg3_name) as TextView
            vegeEgg3_img = view.findViewById(R.id.vege_egg3_img) as ImageView

            view.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            menuClickListener.onMenuClickListener(view!!, adapterPosition)
        }

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): VegeEgg3Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_vege_egg_items3,p0,false)
        return VegeEgg3Holder(view)
    }

    override fun onBindViewHolder(vegeEgg3Holder: VegeEgg3Holder, position: Int) {
        vegeEgg3Holder.vegeEgg3_name.setText(VegeEgg3List!![position].name!!)
        Picasso.get().load(VegeEgg3List[position].image).into(vegeEgg3Holder.vegeEgg3_img)

        vegeEgg3Holder.setClick(object : MenuClickListener {
            override fun onMenuClickListener(view: View, position: Int) {
                //Toast.makeText(context, VegeEgg3List[position].name + " Selected", Toast.LENGTH_SHORT).show()
                common.vegeEgg3Selected = VegeEgg3List.get(position)
                view!!.findNavController().navigate(R.id.action_myMenuFragment_to_vegeEgg3DetailFragment)
            }

        })
    }

    override fun getItemCount(): Int {
        return VegeEgg3List?.size ?:0
    }
}