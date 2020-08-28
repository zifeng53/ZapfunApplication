package com.example.zapfunfoodorderingapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.zapfunfoodorderingapplication.R
import com.example.zapfunfoodorderingapplication.models.OrderStatusModel

class StatusRecyclerAdapter(context: Context, data: List<OrderStatusModel>?) :
    RecyclerView.Adapter<StatusRecyclerAdapter.OrderStatusViewHolder>() {
    private var mContext: Context = context
    private var items: List<OrderStatusModel>? = data
    private var inflater: LayoutInflater = LayoutInflater.from(context)
    private var headlineAdapter: ItemRecyclerAdapter? = null

    override
    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderStatusViewHolder {
        val view = inflater.inflate(R.layout.order_status_list, parent, false)
        return OrderStatusViewHolder(view)
    }

    override
    fun onBindViewHolder(holder: OrderStatusViewHolder, position: Int) {
        val item = items?.get(position)

        holder.tvName.text = item?.orderStatus
        headlineAdapter = ItemRecyclerAdapter(mContext, item?.paperHeadlines)
        holder.rvHeadlines.adapter = headlineAdapter
        holder.rvHeadlines.layoutManager = LinearLayoutManager(mContext)
        holder.ivArrow.setOnClickListener { onItemClicked(item) }
        if (item?.isExpanded!!) {
            holder.rvHeadlines.visibility = View.VISIBLE
            holder.ivArrow.setImageResource(R.drawable.ic_arrow_up)
        } else {
            holder.rvHeadlines.visibility = View.GONE
            holder.ivArrow.setImageResource(R.drawable.ic_arrow_down)
        }
    }

    override
    fun getItemCount(): Int {
        return items?.size ?: 0
    }

    private fun onItemClicked(orderStatusModel: OrderStatusModel?) {
        orderStatusModel?.isExpanded = !orderStatusModel?.isExpanded!!
        notifyDataSetChanged()
    }

    class OrderStatusViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tvOrderStatus)
        var rvHeadlines: RecyclerView = itemView.findViewById(R.id.rvHeadlines)
        var ivArrow: ImageView = itemView.findViewById(R.id.ivArrow)
    }


}