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
import com.example.zapfunfoodorderingapplication.models.MenuChickenModel
import com.squareup.picasso.Picasso

class ChickenAdapter(private val context: Context,
                     private val ChickenList:List<MenuChickenModel>?)
    : RecyclerView.Adapter<ChickenAdapter.ChickenHolder>() {

    inner class ChickenHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {

        var chicken_name: TextView
        var chicken_img: ImageView

        lateinit var menuClickListener: MenuClickListener

        fun setClick(menuClickListener: MenuClickListener) {
            this.menuClickListener = menuClickListener
        }

        init {
            chicken_name = view.findViewById(R.id.chicken_name) as TextView
            chicken_img = view.findViewById(R.id.chicken_img) as ImageView

            view.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            menuClickListener.onMenuClickListener(view!!, adapterPosition)
        }

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ChickenHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_chicken_items,p0,false)
        return ChickenHolder(view)
    }

    override fun onBindViewHolder(chickenHolder: ChickenHolder, position: Int) {
        chickenHolder.chicken_name.setText(ChickenList!![position].name!!)
        Picasso.get().load(ChickenList[position].image).into(chickenHolder.chicken_img)

        chickenHolder.setClick(object : MenuClickListener {
            override fun onMenuClickListener(view: View, position: Int) {
                //Toast.makeText(context, ChickenList[position].name + " Selected", Toast.LENGTH_SHORT).show()
                common.chickenSelected = ChickenList.get(position)
                view!!.findNavController().navigate(R.id.action_myMenuFragment_to_chickenDetailFragment)
            }

        })
    }

    override fun getItemCount(): Int {
        return ChickenList?.size ?:0
    }
}