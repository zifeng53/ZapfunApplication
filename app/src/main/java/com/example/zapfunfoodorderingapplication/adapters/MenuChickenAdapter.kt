package com.example.zapfunfoodorderingapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.zapfunfoodorderingapplication.R
import com.example.zapfunfoodorderingapplication.models.MenuChickenModel
import de.hdodenhof.circleimageview.CircleImageView

class MenuChickenAdapter (internal var context: Context,
                          internal var menuChickenModels: List<MenuChickenModel>) :

    RecyclerView.Adapter<MenuChickenAdapter.MyViewHolder>(){

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var chicken_name: TextView?= null
        var chicken_image: CircleImageView?= null

        init{
            chicken_name = itemView.findViewById(R.id.chicken_name) as TextView
            chicken_image = itemView.findViewById(R.id.chicken_img) as CircleImageView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_chicken_items, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Glide.with(context).load(menuChickenModels.get(position).image).into(holder.chicken_image!!)
        holder.chicken_name!!.setText(menuChickenModels.get(position).name)
    }

    override fun getItemCount(): Int {
        return menuChickenModels.size
    }

    /*override fun getItemViewType(position: Int): Int {
        return if(menuChickenModels.size == 1)
            Common.DEFAULT_COLUMN_COUNT
        else {
            if(menuChickenModels.size % 2 == 0)
                Common.DEFAULT_COLUMN_COUNT
            else
                if(position > 1 && position == menuChickenModels.size -1)
                    Common.FULL_WIDTH_COLUMN
            else
                    Common.DEFAULT_COLUMN_COUNT
        }
    }*/
}