package com.example.zapfunfoodorderingapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.zapfunfoodorderingapplication.R
import com.example.zapfunfoodorderingapplication.callback.MenuClickListener
import com.example.zapfunfoodorderingapplication.models.MenuPork2Model
import com.squareup.picasso.Picasso

class Pork2Adapter(private val context: Context,
                   private val Pork2List:List<MenuPork2Model>?)
    : RecyclerView.Adapter<Pork2Adapter.Pork2Holder>() {

    inner class Pork2Holder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {

        var pork2_name: TextView
        var pork2_img: ImageView

        lateinit var menuClickListener: MenuClickListener

        fun setClick(menuClickListener: MenuClickListener) {
            this.menuClickListener = menuClickListener
        }

        init {
            pork2_name = view.findViewById(R.id.pork2_name) as TextView
            pork2_img = view.findViewById(R.id.pork2_img) as ImageView

            view.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            menuClickListener.onMenuClickListener(view!!, adapterPosition)
        }

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): Pork2Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_pork_items2,p0,false)
        return Pork2Holder(view)
    }

    override fun onBindViewHolder(pork2Holder: Pork2Holder, position: Int) {
        pork2Holder.pork2_name.setText(Pork2List!![position].name!!)
        Picasso.get().load(Pork2List[position].image).into(pork2Holder.pork2_img)

        pork2Holder.setClick(object : MenuClickListener {
            override fun onMenuClickListener(view: View, position: Int) {
                Toast.makeText(context, Pork2List[position].name + " Selected", Toast.LENGTH_SHORT).show()
            }

        })
    }

    override fun getItemCount(): Int {
        return Pork2List?.size ?:0
    }
}