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
import com.example.zapfunfoodorderingapplication.models.MenuPork1Model
import com.squareup.picasso.Picasso

class Pork1Adapter(private val context: Context,
                   private val Pork1List:List<MenuPork1Model>?)
    : RecyclerView.Adapter<Pork1Adapter.Pork1Holder>() {

    inner class Pork1Holder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {

        var pork1_name: TextView
        var pork1_img: ImageView

        lateinit var menuClickListener: MenuClickListener

        fun setClick(menuClickListener: MenuClickListener) {
            this.menuClickListener = menuClickListener
        }

        init {
            pork1_name = view.findViewById(R.id.pork1_name) as TextView
            pork1_img = view.findViewById(R.id.pork1_img) as ImageView

            view.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            menuClickListener.onMenuClickListener(view!!, adapterPosition)
        }

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): Pork1Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_pork_items1,p0,false)
        return Pork1Holder(view)
    }

    override fun onBindViewHolder(pork1Holder: Pork1Holder, position: Int) {
        pork1Holder.pork1_name.setText(Pork1List!![position].name!!)
        Picasso.get().load(Pork1List[position].image).into(pork1Holder.pork1_img)

        pork1Holder.setClick(object : MenuClickListener {
            override fun onMenuClickListener(view: View, position: Int) {
                Toast.makeText(context, Pork1List[position].name + " Selected", Toast.LENGTH_SHORT).show()
            }

        })
    }

    override fun getItemCount(): Int {
        return Pork1List?.size ?:0
    }
}