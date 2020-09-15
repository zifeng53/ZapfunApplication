package com.example.zapfunfoodorderingapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.zapfunfoodorderingapplication.R
import com.example.zapfunfoodorderingapplication.models.MenuFishModel
import de.hdodenhof.circleimageview.CircleImageView

class MenuFishAdapter (internal var context: Context,
                       internal var menuFishModels: List<MenuFishModel>) :

    RecyclerView.Adapter<MenuFishAdapter.MyViewHolder>(){

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var fish_name: TextView?= null
        var fish_image: CircleImageView?= null

        init{
            fish_name = itemView.findViewById(R.id.fish_name) as TextView
            fish_image = itemView.findViewById(R.id.fish_img) as CircleImageView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_fish_items, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Glide.with(context).load(menuFishModels.get(position).image).into(holder.fish_image!!)
        holder.fish_name!!.setText(menuFishModels.get(position).name)
    }

    override fun getItemCount(): Int {
        return menuFishModels.size
    }
}