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
import com.example.zapfunfoodorderingapplication.models.MenuVegeEgg2Model
import com.squareup.picasso.Picasso

class VegeEgg2Adapter(private val context: Context,
                      private val VegeEgg2List:List<MenuVegeEgg2Model>?)
    : RecyclerView.Adapter<VegeEgg2Adapter.VegeEgg2Holder>() {

    inner class VegeEgg2Holder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {

        var vegeEgg2_name: TextView
        var vegeEgg2_img: ImageView

        lateinit var menuClickListener: MenuClickListener

        fun setClick(menuClickListener: MenuClickListener) {
            this.menuClickListener = menuClickListener
        }

        init {
            vegeEgg2_name = view.findViewById(R.id.vege_egg2_name) as TextView
            vegeEgg2_img = view.findViewById(R.id.vege_egg2_img) as ImageView

            view.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            menuClickListener.onMenuClickListener(view!!, adapterPosition)
        }

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): VegeEgg2Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_vege_egg_items2,p0,false)
        return VegeEgg2Holder(view)
    }

    override fun onBindViewHolder(vegeEgg2Holder: VegeEgg2Holder, position: Int) {
        vegeEgg2Holder.vegeEgg2_name.setText(VegeEgg2List!![position].name!!)
        Picasso.get().load(VegeEgg2List[position].image).into(vegeEgg2Holder.vegeEgg2_img)

        vegeEgg2Holder.setClick(object : MenuClickListener {
            override fun onMenuClickListener(view: View, position: Int) {
                //Toast.makeText(context, VegeEgg2List[position].name + " Selected", Toast.LENGTH_SHORT).show()
                common.vegeEgg2Selected = VegeEgg2List.get(position)
                view!!.findNavController().navigate(R.id.action_myMenuFragment_to_vegeEgg2DetailFragment)
            }

        })
    }

    override fun getItemCount(): Int {
        return VegeEgg2List?.size ?:0
    }
}