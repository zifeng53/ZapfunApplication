package com.example.zapfunfoodorderingapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.zapfunfoodorderingapplication.R
import com.example.zapfunfoodorderingapplication.models.MenuPork2Model
import de.hdodenhof.circleimageview.CircleImageView

class MenuPork2Adapter(internal var context: Context,
                       internal var menuPork2Models: List<MenuPork2Model>) :
    RecyclerView.Adapter<MenuPork2Adapter.MyViewHolder>(){

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var pork2_name: TextView?= null
        var pork2_image: CircleImageView?= null

        init{
            pork2_name = itemView.findViewById(R.id.pork2_name) as TextView
            pork2_image = itemView.findViewById(R.id.pork2_img) as CircleImageView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_pork_items2, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Glide.with(context).load(menuPork2Models.get(position).image).into(holder.pork2_image!!)
        holder.pork2_name!!.setText(menuPork2Models.get(position).name)
    }

    override fun getItemCount(): Int {
        return menuPork2Models.size
    }
}