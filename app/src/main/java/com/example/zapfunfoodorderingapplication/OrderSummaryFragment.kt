package com.example.zapfunfoodorderingapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zapfunfoodorderingapplication.adapters.OrderItemHeaderRecyclerAdapter
import com.example.zapfunfoodorderingapplication.utils.JsonHelper2
import kotlinx.android.synthetic.main.fragment_my_order.*
import kotlinx.android.synthetic.main.fragment_my_order.recyclerOrderList
import kotlinx.android.synthetic.main.fragment_order_summary.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class OrderSummaryFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_order_summary, container, false)
        val btnBack: ImageView = view.findViewById(R.id.OSBtnBack)
        btnBack.setOnClickListener{view : View ->
            view.findNavController().navigate(R.id.action_orderSummaryFragment_to_myHistoryFragment)}
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        OSRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = OrderItemHeaderRecyclerAdapter((this.context!!), JsonHelper2(this.context!!).getListData())
        }
    }
}