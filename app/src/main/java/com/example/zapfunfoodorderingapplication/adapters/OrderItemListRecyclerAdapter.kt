package com.example.zapfunfoodorderingapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.zapfunfoodorderingapplication.R
import com.example.zapfunfoodorderingapplication.models.CartModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class OrderItemListRecyclerAdapter(internal var context: Context, internal var data: List<CartModel>) :
    RecyclerView.Adapter<OrderItemListRecyclerAdapter.OrderListViewHolder>(){
    private var mContext: Context = context
    private var items: List<CartModel>? = data
    private var inflater: LayoutInflater = LayoutInflater.from(context)

    class OrderListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var tvItem: TextView = itemView.findViewById(R.id.textView28)
        var tvPrice: TextView = itemView.findViewById(R.id.textView41)
    }

    override
    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderListViewHolder{
        return OrderListViewHolder(LayoutInflater.from(context).inflate(R.layout.myorder_item_list,parent,false))
    }

    override
    fun onBindViewHolder(holder: OrderListViewHolder, position: Int){
        val item = items?.get(position)
        var riceName = item?.rice_type
        var dish1Name = item?.dish1
        var dish2Name = item?.dish2
        var dish3Name = item?.dish3
        var dish4Name = item?.dish4

        holder.tvItem.text = "Rice: " + riceName + "\n" + dish1Name + "\n" + dish2Name + "\n" + dish3Name + "\n" + dish4Name

        //holder.tvPrice.text = item?.unit_price
    }

    override
    fun getItemCount(): Int {
        return data.size
    }
}