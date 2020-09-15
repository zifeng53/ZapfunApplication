package com.example.zapfunfoodorderingapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.zapfunfoodorderingapplication.R
import com.example.zapfunfoodorderingapplication.models.MenuVegeEgg4Model
import de.hdodenhof.circleimageview.CircleImageView

class MenuVegeEgg4Adapter(internal var context: Context,
                          internal var menuVegeEgg4Models: List<MenuVegeEgg4Model>) :
    RecyclerView.Adapter<MenuVegeEgg4Adapter.MyViewHolder>(){

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var vege_egg4_name: TextView?= null
        var vege_egg4_image: CircleImageView?= null

        init{
            vege_egg4_name = itemView.findViewById(R.id.vege_egg4_name) as TextView
            vege_egg4_image = itemView.findViewById(R.id.vege_egg4_img) as CircleImageView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_vege_egg_items4, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Glide.with(context).load(menuVegeEgg4Models.get(position).image).into(holder.vege_egg4_image!!)
        holder.vege_egg4_name!!.setText(menuVegeEgg4Models.get(position).name)
    }

    override fun getItemCount(): Int {
        return menuVegeEgg4Models.size
    }
}