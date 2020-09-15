package com.example.zapfunfoodorderingapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.zapfunfoodorderingapplication.R
import com.example.zapfunfoodorderingapplication.models.MenuVegeEgg2Model
import de.hdodenhof.circleimageview.CircleImageView

class MenuVegeEgg2Adapter(internal var context: Context,
                          internal var menuVegeEgg2Models: List<MenuVegeEgg2Model>) :
    RecyclerView.Adapter<MenuVegeEgg2Adapter.MyViewHolder>(){

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var vege_egg2_name: TextView?= null
        var vege_egg2_image: CircleImageView?= null

        init{
            vege_egg2_name = itemView.findViewById(R.id.vege_egg2_name) as TextView
            vege_egg2_image = itemView.findViewById(R.id.vege_egg2_img) as CircleImageView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_vege_egg_items2, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Glide.with(context).load(menuVegeEgg2Models.get(position).image).into(holder.vege_egg2_image!!)
        holder.vege_egg2_name!!.setText(menuVegeEgg2Models.get(position).name)
    }

    override fun getItemCount(): Int {
        return menuVegeEgg2Models.size
    }
}