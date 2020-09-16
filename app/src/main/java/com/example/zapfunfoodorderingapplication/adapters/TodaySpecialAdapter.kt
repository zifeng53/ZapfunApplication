package com.example.zapfunfoodorderingapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.zapfunfoodorderingapplication.R
import com.example.zapfunfoodorderingapplication.callback.MenuClickListener
import com.example.zapfunfoodorderingapplication.common.common
import com.example.zapfunfoodorderingapplication.models.MenuTodaySpecialModel
import com.squareup.picasso.Picasso

class TodaySpecialAdapter(private val context: Context,
                          private val TodaySpecialList:List<MenuTodaySpecialModel>?)
    : RecyclerView.Adapter<TodaySpecialAdapter.TodaySpecialHolder>() {

    inner class TodaySpecialHolder(view: View):RecyclerView.ViewHolder(view), View.OnClickListener {

        var today_special_name: TextView
        var today_special_img: ImageView

        lateinit var menuClickListener: MenuClickListener

        fun setClick(menuClickListener: MenuClickListener) {
            this.menuClickListener = menuClickListener
        }

        init {
            today_special_name = view.findViewById(R.id.txt_today_special) as TextView
            today_special_img = view.findViewById(R.id.todaySpecial_image) as ImageView

            view.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            menuClickListener.onMenuClickListener(view!!, adapterPosition)
        }

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TodaySpecialHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_today_special_items,p0,false)
        return TodaySpecialHolder(view)
    }

    override fun onBindViewHolder(todaySpecialHolder: TodaySpecialHolder, position: Int) {
        todaySpecialHolder.today_special_name.setText(TodaySpecialList!![position].name!!)
        Picasso.get().load(TodaySpecialList[position].image).into(todaySpecialHolder.today_special_img)

        //var menuSelected: MenuTodaySpecialModel?=null

        todaySpecialHolder.setClick(object : MenuClickListener {
            override fun onMenuClickListener(view: View, position: Int) {
                //Toast.makeText(context, TodaySpecialList[position].name + " Selected", Toast.LENGTH_SHORT).show()
                common.menuSelected = TodaySpecialList.get(position)
                view!!.findNavController().navigate(R.id.action_myMenuFragment_to_menuDetailFragment)
            }

        })
    }

    override fun getItemCount(): Int {
        return TodaySpecialList?.size ?:0
    }
}