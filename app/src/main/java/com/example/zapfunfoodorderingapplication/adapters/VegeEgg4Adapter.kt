package com.example.zapfunfoodorderingapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.zapfunfoodorderingapplication.R
import com.example.zapfunfoodorderingapplication.callback.MenuClickListener
import com.example.zapfunfoodorderingapplication.models.MenuVegeEgg4Model
import com.squareup.picasso.Picasso

class VegeEgg4Adapter(private val context: Context,
                      private val VegeEgg4List:List<MenuVegeEgg4Model>?)
    : RecyclerView.Adapter<VegeEgg4Adapter.VegeEgg4Holder>() {

    inner class VegeEgg4Holder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {

        var vegeEgg4_name: TextView
        var vegeEgg4_img: ImageView

        lateinit var menuClickListener: MenuClickListener

        fun setClick(menuClickListener: MenuClickListener) {
            this.menuClickListener = menuClickListener
        }

        init {
            vegeEgg4_name = view.findViewById(R.id.vege_egg4_name) as TextView
            vegeEgg4_img = view.findViewById(R.id.vege_egg4_img) as ImageView

            view.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            menuClickListener.onMenuClickListener(view!!, adapterPosition)
        }

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): VegeEgg4Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_vege_egg_items4,p0,false)
        return VegeEgg4Holder(view)
    }

    override fun onBindViewHolder(vegeEgg4Holder: VegeEgg4Holder, position: Int) {
        vegeEgg4Holder.vegeEgg4_name.setText(VegeEgg4List!![position].name!!)
        Picasso.get().load(VegeEgg4List[position].image).into(vegeEgg4Holder.vegeEgg4_img)

        vegeEgg4Holder.setClick(object : MenuClickListener {
            override fun onMenuClickListener(view: View, position: Int) {
                Toast.makeText(context, VegeEgg4List[position].name + " Selected", Toast.LENGTH_SHORT).show()
            }

        })
    }

    override fun getItemCount(): Int {
        return VegeEgg4List?.size ?:0
    }
}