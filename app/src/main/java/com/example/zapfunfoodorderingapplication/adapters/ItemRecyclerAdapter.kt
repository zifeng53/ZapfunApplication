package com.example.zapfunfoodorderingapplication.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.zapfunfoodorderingapplication.*
import com.example.zapfunfoodorderingapplication.models.OrderModel
import java.lang.Exception

class ItemRecyclerAdapter(internal var context: Context, internal var data: List<OrderModel>) :
    RecyclerView.Adapter<ItemRecyclerAdapter.HistoryItemViewHolder>() {
    private var mContext: Context = context
    private var items: List<OrderModel>? = data
    private var inflater: LayoutInflater = LayoutInflater.from(context)
    var OrderID = ""
    val delivery_fee : Double = 5.00


    inner class HistoryItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvRecipient: TextView = itemView.findViewById(R.id.tvRecipientName)
        var tvAmount: TextView = itemView.findViewById(R.id.tvAmount)
        var tvItemDetails: TextView = itemView.findViewById(R.id.tvItemDetails)
        var tvOrderDate: TextView = itemView.findViewById(R.id.OStvOrderDate)
        var tvStatus: TextView = itemView.findViewById(R.id.lblOSStatus)
        var order_id: TextView = itemView.findViewById(R.id.hiddenCtrl)
    }

    override
    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryItemViewHolder {
        return HistoryItemViewHolder(LayoutInflater.from(context).inflate(R.layout.order_item_list, parent, false)
        )
    }
    override
    fun onBindViewHolder(holder: HistoryItemViewHolder, position: Int) {
        val item = items?.get(position)
        holder.tvRecipient.text = item?.rec_name
        var amount = item?.sub_total
        if (amount != null) {
            holder.tvAmount.text = "RM" + (amount + delivery_fee).toString() + "0"
        }
        holder.tvItemDetails.text = item?.address
        holder.tvOrderDate.text = item?.order_date + "," + item?.order_time
        holder.tvStatus.text = item?.order_status
        holder.order_id.text = item?.order_id
        OrderID = holder.order_id.text.toString()
        var status = item?.order_status.toString()
        if (status == "delivered"){
            holder.tvStatus.setBackgroundResource(R.drawable.bg_roundedgrey)
        }else {
            holder.tvStatus.setBackgroundResource(R.drawable.bg_rounded)
        }


        holder.itemView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                var orderID: TextView? = v?.findViewById(R.id.hiddenCtrl)
                var id = orderID?.text.toString()

                    val intent = Intent(mContext, OrderSummaryActivity::class.java)
                    intent.putExtra("OrderIdValue", id)
                    mContext.startActivity(intent)
            }
        })
    }

    override
    fun getItemCount(): Int {
        //return items?.size?:0
        return data.size
    }
}