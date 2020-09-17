package com.example.zapfunfoodorderingapplication.adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.zapfunfoodorderingapplication.PriceObject
import com.example.zapfunfoodorderingapplication.R
import com.example.zapfunfoodorderingapplication.models.CartMenuModel


class OrderItemListRecyclerAdapter(internal var context: Context, internal var data: List<CartMenuModel>) :
    RecyclerView.Adapter<OrderItemListRecyclerAdapter.OrderListViewHolder>(){
    private var mContext: Context = context
    private var items: List<CartMenuModel>? = data
    private var inflater: LayoutInflater = LayoutInflater.from(context)
    var totalNoShipping = 0.0

    class OrderListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var tvItem: TextView = itemView.findViewById(R.id.textView28)
        var tvPrice: TextView = itemView.findViewById(R.id.textView41)
        var tvTotal: TextView = itemView.findViewById(R.id.textView39)
    }

    override
    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderListViewHolder{
        return OrderListViewHolder(LayoutInflater.from(context).inflate(R.layout.myorder_item_list,parent,false))
    }

    override
    fun onBindViewHolder(holder: OrderListViewHolder, position: Int){
        val item = items?.get(position)
        //var riceName = item?.rice_type
        var dishName = item?.item
        val dishPrice = item?.price

        holder.tvItem.text = dishName
        holder.tvPrice.text = "RM " + dishPrice.toString()
        var dish_price = dishPrice.toString()

        //holder.tvItem.text = "Rice: " + riceName + "\n" + dish1Name + "\n" + dish2Name + "\n" + dish3Name + "\n" + dish4Name

        //holder.tvPrice.text = item?.unit_price
        totalNoShipping += dish_price.toDouble()

        if(position==data.size-1) {
            holder.tvTotal.text = "Total Price: RM " + totalNoShipping.toString()
            PriceObject.totalPrice = totalNoShipping
        }
    }

    override
    fun getItemCount(): Int {
        return data.size
    }
}