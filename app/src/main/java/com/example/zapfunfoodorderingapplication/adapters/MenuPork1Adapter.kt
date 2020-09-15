package com.example.zapfunfoodorderingapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.zapfunfoodorderingapplication.R
import com.example.zapfunfoodorderingapplication.models.MenuPork1Model
import de.hdodenhof.circleimageview.CircleImageView

class MenuPork1Adapter(internal var context: Context,
                       internal var menuPork1Models: List<MenuPork1Model>) :
    RecyclerView.Adapter<MenuPork1Adapter.MyViewHolder>(){

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var pork1_name: TextView?= null
        var pork1_image: CircleImageView?= null

        init{
            pork1_name = itemView.findViewById(R.id.pork1_name) as TextView
            pork1_image = itemView.findViewById(R.id.pork1_img) as CircleImageView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_pork_items1, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Glide.with(context).load(menuPork1Models.get(position).image).into(holder.pork1_image!!)
        holder.pork1_name!!.setText(menuPork1Models.get(position).name)
    }

    override fun getItemCount(): Int {
        return menuPork1Models.size
    }

    /*override fun getItemViewType(position: Int): Int {
        return if(menuPork1Models.size == 1)
            Common.DEFAULT_COLUMN_COUNT
        else {
            if(menuPork1Models.size % 2 == 0)
                Common.DEFAULT_COLUMN_COUNT
            else
                if(position > 1 && position == menuPork1Models.size -1)
                    Common.FULL_WIDTH_COLUMN
                else
                    Common.DEFAULT_COLUMN_COUNT
        }
    }*/
}