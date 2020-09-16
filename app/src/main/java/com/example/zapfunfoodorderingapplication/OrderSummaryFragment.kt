package com.example.zapfunfoodorderingapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_order_summary.*

class OrderSummaryFragment : Fragment() {
    //Creating member variables
    //private var mFirebaseDatabase: DatabaseReference?=null
    //private var mFirebaseInstance: FirebaseDatabase?=null

    private var mDatabase: DatabaseReference? = null
    private var mMessageReference: DatabaseReference? = null


    var userId:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
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

            // ...
            mDatabase = FirebaseDatabase.getInstance().reference
            mMessageReference = FirebaseDatabase.getInstance().getReference("Payment")


            //adapter = OrderItemHeaderRecyclerAdapter((this.context!!), JsonHelper2(this.context!!).getListData())
        }
    }


}