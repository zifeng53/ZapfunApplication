package com.example.zapfunfoodorderingapplication

import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.Observer
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.zapfunfoodorderingapplication.adapters.ItemRecyclerAdapter
import com.example.zapfunfoodorderingapplication.utils.HistoryOrderViewModel
import com.google.firebase.database.*
import org.json.JSONObject

class MyHistoryFragment : Fragment() {
    private lateinit var historyOrderViewModel: HistoryOrderViewModel
    var  HistoryRecyclerView: RecyclerView?=null

    private var mDatabase: DatabaseReference? = null
    private var mOrderReference: DatabaseReference? = null

    var childList = ArrayList<JSONObject>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_my_history, container, false)

        historyOrderViewModel = ViewModelProviders.of(this).get(HistoryOrderViewModel::class.java)

        historyListView(view)
        //Bind data
        historyOrderViewModel.historyList.observe(viewLifecycleOwner, Observer {
            val listData = it
            val adapter = ItemRecyclerAdapter(requireContext(), listData)
            HistoryRecyclerView!!.adapter = adapter
        })
        return view
    }

    private fun historyListView(view:View) {
        HistoryRecyclerView = view.findViewById(R.id.MHrecyclerView) as RecyclerView
        HistoryRecyclerView!!.setHasFixedSize(true)
        HistoryRecyclerView!!.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    }

    companion object {
        fun newInstance(): MyHistoryFragment = MyHistoryFragment()
    }
}