package com.example.zapfunfoodorderingapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.zapfunfoodorderingapplication.OrderSummaryFragment
import com.example.zapfunfoodorderingapplication.R
import com.example.zapfunfoodorderingapplication.models.OrderItemModel

class ItemRecyclerAdapter(context: Context, data: List<OrderItemModel>?) :
    RecyclerView.Adapter<ItemRecyclerAdapter.HeadlinesViewHolder>() {
    private var items: List<OrderItemModel>? = data
    private var inflater: LayoutInflater = LayoutInflater.from(context)

    override
    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeadlinesViewHolder {
        val view = inflater.inflate(R.layout.order_item_list, parent, false)
        return HeadlinesViewHolder(view)
    }

    override
    fun onBindViewHolder(holder: HeadlinesViewHolder, position: Int) {
        val item = items?.get(position)

        holder.tvRecipient.text = item?.recipient
        holder.tvAmount.text = item?.amount
        holder.tvItemDetails.text = item?.item_details
        holder.tvOrderDate.text = item?.order_date

        //

        holder.itemView.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v:View?){
                val btnAddress: RelativeLayout = v!!.findViewById(R.id.expandable_layout)
                    v.findNavController().navigate(R.id.action_myHistoryFragment_to_orderSummaryFragment)
            }
        })
    }

    override
    fun getItemCount(): Int {
        return items?.size?:0
    }

    class HeadlinesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvRecipient: TextView = itemView.findViewById(R.id.tvRecipientName)
        var tvAmount: TextView = itemView.findViewById(R.id.tvAmount)
        var tvItemDetails: TextView = itemView.findViewById(R.id.tvItemDetails)
        var tvOrderDate: TextView = itemView.findViewById(R.id.tvOrderDate)

    }
}