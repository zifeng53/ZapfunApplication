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
import com.example.zapfunfoodorderingapplication.models.MenuVegeEgg1Model
import com.squareup.picasso.Picasso

class VegeEgg1Adapter(private val context: Context,
                      private val VegeEgg1List:List<MenuVegeEgg1Model>?)
    : RecyclerView.Adapter<VegeEgg1Adapter.VegeEgg1Holder>() {

    inner class VegeEgg1Holder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {

        var vegeEgg1_name: TextView
        var vegeEgg1_img: ImageView

        lateinit var menuClickListener: MenuClickListener

        fun setClick(menuClickListener: MenuClickListener) {
            this.menuClickListener = menuClickListener
        }

        init {
            vegeEgg1_name = view.findViewById(R.id.vege_egg1_name) as TextView
            vegeEgg1_img = view.findViewById(R.id.vege_egg1_img) as ImageView

            view.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            menuClickListener.onMenuClickListener(view!!, adapterPosition)
        }

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): VegeEgg1Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_vege_egg_items1,p0,false)
        return VegeEgg1Holder(view)
    }

    override fun onBindViewHolder(vegeEgg1Holder: VegeEgg1Holder, position: Int) {
        vegeEgg1Holder.vegeEgg1_name.setText(VegeEgg1List!![position].name!!)
        Picasso.get().load(VegeEgg1List[position].image).into(vegeEgg1Holder.vegeEgg1_img)

        vegeEgg1Holder.setClick(object : MenuClickListener {
            override fun onMenuClickListener(view: View, position: Int) {
                //Toast.makeText(context, VegeEgg1List[position].name + " Selected", Toast.LENGTH_SHORT).show()
                common.vegeEgg1Selected = VegeEgg1List.get(position)
                view!!.findNavController().navigate(R.id.action_myMenuFragment_to_vegeEgg1DetailFragment)
            }

        })
    }

    override fun getItemCount(): Int {
        return VegeEgg1List?.size ?:0
    }
}