package com.example.zapfunfoodorderingapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.zapfunfoodorderingapplication.R
import com.example.zapfunfoodorderingapplication.models.OrderItemListModel

class OrderItemListRecyclerAdapter(context: Context, data: List<OrderItemListModel>?) :
    RecyclerView.Adapter<OrderItemListRecyclerAdapter.OrderListViewHolder>(){
    private var items: List<OrderItemListModel>? = data
    private var inflater: LayoutInflater = LayoutInflater.from(context)

    override
    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderListViewHolder{
        val view = inflater.inflate(R.layout.myorder_item_list, parent, false)
        return OrderListViewHolder(view)
    }

    override
    fun onBindViewHolder(holder: OrderListViewHolder, position: Int){
        val item = items?.get(position)

        holder.tvItem.text = item?.item_detail
        holder.tvPrice.text = item?.unit_price
    }

    override
    fun getItemCount(): Int {
        return items?.size?:0
    }

    class OrderListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var tvItem: TextView = itemView.findViewById(R.id.textView28)
        var tvPrice: TextView = itemView.findViewById(R.id.textView41)
    }
}