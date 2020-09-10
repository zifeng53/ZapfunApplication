package com.example.zapfunfoodorderingapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.zapfunfoodorderingapplication.R
import com.example.zapfunfoodorderingapplication.models.MenuTodaySpecialModel
import de.hdodenhof.circleimageview.CircleImageView

class MenuTodaySpecialAdapter (internal var context: Context,
                               internal var menuTodaySpecialModels: List<MenuTodaySpecialModel>) :

    RecyclerView.Adapter<MenuTodaySpecialAdapter.MyViewHolder>(){

    inner class MyViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
        //@BindView(R.id.txt_category_name)
        var todaySpecial_name: TextView?= null
        //@BindView(R.id.category_image)
        var todaySpecial_image: CircleImageView?= null

        //var unbinder:Unbinder
        init{
            //unbinder = ButterKnife.bind(this, itemView)
            todaySpecial_name = itemView.findViewById(R.id.txt_today_special) as TextView
            todaySpecial_image = itemView.findViewById(R.id.todaySpecial_image) as CircleImageView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_today_special_items, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Glide.with(context).load(menuTodaySpecialModels.get(position).image).into(holder.todaySpecial_image!!)
        holder.todaySpecial_name!!.setText(menuTodaySpecialModels.get(position).name)
    }

    override fun getItemCount(): Int {
        return menuTodaySpecialModels.size
    }

}