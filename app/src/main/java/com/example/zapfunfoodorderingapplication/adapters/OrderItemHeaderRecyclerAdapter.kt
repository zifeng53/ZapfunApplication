package com.example.zapfunfoodorderingapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.zapfunfoodorderingapplication.R
import com.example.zapfunfoodorderingapplication.models.OrderItemListHeaderModel

class OrderItemHeaderRecyclerAdapter(context: Context, data: List<OrderItemListHeaderModel>?) :
   RecyclerView.Adapter<OrderItemHeaderRecyclerAdapter.OrderItemHeaderViewHolder>(){
    private var mContext: Context = context
    private var items: List<OrderItemListHeaderModel>?=data
    private var inflater: LayoutInflater = LayoutInflater.from(context)
    private var headlineAdapters: OrderItemListRecyclerAdapter? = null

    override
    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderItemHeaderViewHolder{
        val view = inflater.inflate(R.layout.myorder_item_list_header, parent, false)
        return OrderItemHeaderViewHolder(view)
    }

    override
    fun onBindViewHolder(holder: OrderItemHeaderViewHolder, position: Int){
        val item = items?.get(position)

        holder.tvQty.text = item?.qty
        holder.tvPrice.text = item?.price
        headlineAdapters = OrderItemListRecyclerAdapter(mContext, item?.listHeadlines)
        holder.rvHeaders.adapter = headlineAdapters
        holder.rvHeaders.layoutManager = LinearLayoutManager(mContext)
        holder.rlHeader.setOnClickListener{onItemClicked(item)}
        if (item?.isExpanded!!){
            holder.rvHeaders.visibility = View.VISIBLE
        } else{
            holder.rvHeaders.visibility = View.GONE
        }
    }

    override
    fun getItemCount(): Int {
        return items?.size ?: 0
    }

    private fun onItemClicked(orderItemListHeaderModel: OrderItemListHeaderModel?) {
        orderItemListHeaderModel?.isExpanded = !orderItemListHeaderModel?.isExpanded!!
        notifyDataSetChanged()
    }

    class OrderItemHeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var tvQty: TextView = itemView.findViewById(R.id.textView38)
        var tvPrice: TextView = itemView.findViewById(R.id.textView39)
        var rvHeaders: RecyclerView = itemView.findViewById(R.id.recyclerListHeader)
        var rlHeader: RelativeLayout = itemView.findViewById(R.id.rlHeader)
    }


}