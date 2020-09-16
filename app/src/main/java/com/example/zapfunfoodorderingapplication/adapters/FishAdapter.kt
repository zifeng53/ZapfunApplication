package com.example.zapfunfoodorderingapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.zapfunfoodorderingapplication.R
import com.example.zapfunfoodorderingapplication.callback.MenuClickListener
import com.example.zapfunfoodorderingapplication.common.common
import com.example.zapfunfoodorderingapplication.models.MenuFishModel
import com.squareup.picasso.Picasso

class FishAdapter(private val context: Context,
                  private val FishList:List<MenuFishModel>?)
    : RecyclerView.Adapter<FishAdapter.FishHolder>() {

    inner class FishHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {

        var fish_name: TextView
        var fish_img: ImageView

        lateinit var menuClickListener: MenuClickListener

        fun setClick(menuClickListener: MenuClickListener) {
            this.menuClickListener = menuClickListener
        }

        init {
            fish_name = view.findViewById(R.id.fish_name) as TextView
            fish_img = view.findViewById(R.id.fish_img) as ImageView

            view.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            menuClickListener.onMenuClickListener(view!!, adapterPosition)
        }

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): FishHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_fish_items,p0,false)
        return FishHolder(view)
    }

    override fun onBindViewHolder(fishHolder: FishHolder, position: Int) {
        fishHolder.fish_name.setText(FishList!![position].name!!)
        Picasso.get().load(FishList[position].image).into(fishHolder.fish_img)

        fishHolder.setClick(object : MenuClickListener {
            override fun onMenuClickListener(view: View, position: Int) {
                //Toast.makeText(context, FishList[position].name + " Selected", Toast.LENGTH_SHORT).show()
                common.fishSelected = FishList.get(position)
                view!!.findNavController().navigate(R.id.action_myMenuFragment_to_fishDetailFragment)
            }

        })
    }

    override fun getItemCount(): Int {
        return FishList?.size ?:0
    }
}