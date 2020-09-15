package com.example.zapfunfoodorderingapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.zapfunfoodorderingapplication.R
import com.example.zapfunfoodorderingapplication.models.MenuVegeEgg3Model
import de.hdodenhof.circleimageview.CircleImageView

class MenuVegeEgg3Adapter(internal var context: Context,
                          internal var menuVegeEgg3Models: List<MenuVegeEgg3Model>) :
    RecyclerView.Adapter<MenuVegeEgg3Adapter.MyViewHolder>(){

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var vege_egg3_name: TextView?= null
        var vege_egg3_image: CircleImageView?= null

        init{
            vege_egg3_name = itemView.findViewById(R.id.vege_egg3_name) as TextView
            vege_egg3_image = itemView.findViewById(R.id.vege_egg3_img) as CircleImageView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_vege_egg_items3, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Glide.with(context).load(menuVegeEgg3Models.get(position).image).into(holder.vege_egg3_image!!)
        holder.vege_egg3_name!!.setText(menuVegeEgg3Models.get(position).name)
    }

    override fun getItemCount(): Int {
        return menuVegeEgg3Models.size
    }
}