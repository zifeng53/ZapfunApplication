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


    inner class HistoryItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvRecipient: TextView = itemView.findViewById(R.id.tvRecipientName)
        var tvAmount: TextView = itemView.findViewById(R.id.tvAmount)
        var tvItemDetails: TextView = itemView.findViewById(R.id.tvItemDetails)
        var tvOrderDate: TextView = itemView.findViewById(R.id.OStvOrderDate)
        var tvStatus: TextView = itemView.findViewById(R.id.lblOSStatus)
        var order_id: TextView = itemView.findViewById(R.id.hiddenCtrl)

        //var itemBar: TextView = itemView.findViewById(R.id.HistoryExpandable_layout)


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
        holder.tvAmount.text = "RM" + item?.sub_total.toString()
        holder.tvItemDetails.text = item?.address
        holder.tvOrderDate.text = item?.order_date + "," + item?.order_time
        holder.tvStatus.text = item?.order_status
        holder.order_id.text = item?.order_id
        OrderID = holder.order_id.text.toString()

        holder.itemView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                var orderID: TextView? = v?.findViewById(R.id.hiddenCtrl)
                var id = orderID?.text.toString()
                try {

                    val intent = Intent(mContext, OrderSummaryActivity::class.java)
                    intent.putExtra("OrderIdValue", id)
                    mContext.startActivity(intent)
                }catch (e:Exception){
                    var i = 1
                }
            }
        })
    }


    override
    fun getItemCount(): Int {
        //return items?.size?:0
        return data.size
    }


}