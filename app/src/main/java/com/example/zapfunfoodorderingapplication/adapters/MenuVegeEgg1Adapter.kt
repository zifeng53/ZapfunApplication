package com.example.zapfunfoodorderingapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.zapfunfoodorderingapplication.R
import com.example.zapfunfoodorderingapplication.models.MenuVegeEgg1Model
import de.hdodenhof.circleimageview.CircleImageView

class MenuVegeEgg1Adapter(internal var context: Context,
                          internal var menuVegeEgg1Models: List<MenuVegeEgg1Model>) :
    RecyclerView.Adapter<MenuVegeEgg1Adapter.MyViewHolder>(){

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var vege_egg1_name: TextView?= null
        var vege_egg1_image: CircleImageView?= null

        init{
            vege_egg1_name = itemView.findViewById(R.id.vege_egg1_name) as TextView
            vege_egg1_image = itemView.findViewById(R.id.vege_egg1_img) as CircleImageView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_vege_egg_items1, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Glide.with(context).load(menuVegeEgg1Models.get(position).image).into(holder.vege_egg1_image!!)
        holder.vege_egg1_name!!.setText(menuVegeEgg1Models.get(position).name)
    }

    override fun getItemCount(): Int {
        return menuVegeEgg1Models.size
    }
}