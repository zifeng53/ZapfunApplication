package com.example.zapfunfoodorderingapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zapfunfoodorderingapplication.adapters.StatusRecyclerAdapter
import com.example.zapfunfoodorderingapplication.utils.JsonHelper
import kotlinx.android.synthetic.main.fragment_my_history.*


class MyHistoryFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_my_history, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = StatusRecyclerAdapter((this.context!!), JsonHelper(this.context!!).getHistoryData())
        }
    }
    companion object {
        fun newInstance(): MyHistoryFragment = MyHistoryFragment()
    }
}