package com.example.zapfunfoodorderingapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.zapfunfoodorderingapplication.Order
import com.example.zapfunfoodorderingapplication.R
import com.example.zapfunfoodorderingapplication.models.OrderModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.contact_info_dialog.view.*

class ItemRecyclerAdapter(internal var context: Context, internal var data: List<OrderModel>) :
    RecyclerView.Adapter<ItemRecyclerAdapter.HistoryItemViewHolder>() {
    private var mContext: Context = context
    private var items: List<OrderModel>? = data
    private var inflater: LayoutInflater = LayoutInflater.from(context)


    class HistoryItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvRecipient: TextView = itemView.findViewById(R.id.tvRecipientName)
        var tvAmount: TextView = itemView.findViewById(R.id.tvAmount)
        var tvItemDetails: TextView = itemView.findViewById(R.id.tvItemDetails)
        var tvOrderDate: TextView = itemView.findViewById(R.id.tvOrderDate)
        var tvStatus: TextView = itemView.findViewById(R.id.lblOSStatus)
        //var itemBar: TextView = itemView.findViewById(R.id.HistoryExpandable_layout)


    }

    //override
    //fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeadlinesViewHolder {
    //    return HeadlinesViewHolder(LayoutInflater.from(context).inflate(R.layout.order_item_list, parent, false))
    //}
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
        holder.tvItemDetails.text = item?.address.toString()
        holder.tvOrderDate.text = item?.order_date + "," + item?.order_time
        holder.tvStatus.text = item?.order_status
        //var id:String?= item?.order_id

        holder.itemView.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v:View?){
              //  val HistoryList: RelativeLayout = v!!.findViewById(R.id.HistoryExpandable_layout)
                var tvRecipient: TextView? = v?.findViewById(R.id.tvRecipientName)
                v!!.findNavController().navigate(R.id.action_myHistoryFragment_to_orderSummaryFragment)

                //val activity=v!!.context as AppCompatActivity
                //val OrderSummaryFragment = OrderSummaryFragment()
                //activity.supportFragmentManager.beginTransaction().add(R.id.frame_layout, OrderSummaryFragment).commit()
            }
        })
    }


    override
    fun getItemCount(): Int {
        //return items?.size?:0
        return data.size
    }


}