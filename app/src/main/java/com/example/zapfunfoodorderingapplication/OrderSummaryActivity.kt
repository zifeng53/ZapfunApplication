package com.example.zapfunfoodorderingapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.zapfunfoodorderingapplication.models.PaymentModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_order_summary.*


class OrderSummaryActivity : AppCompatActivity() {
    //Creating member variables

    var OrderID = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_summary)

        val bundle: Bundle? = intent.extras
        OrderID = bundle?.get("OrderIdValue").toString()

        OSBtnBack.setOnClickListener {
                val fragment = MyHistoryFragment()
                val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.container, fragment)
                transaction.commit()
        }

    }

    private fun readData(){

        //Read Order Data
        FirebaseDatabase.getInstance().reference.child("Order")
            .orderByChild("order_id").equalTo(OrderID)
            .addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                val map = p0.value as Map<String,Any>
                val orderData = map[OrderID] as Map<String,Any>
                lblOSSubTotalShow.text = orderData["sub_total"].toString()
                lblOSDeliveryFeeShow.text = orderData["delivery_fee"].toString()
                lblOSOrderDateTimeShow.text = orderData["order_time"].toString()
                lblOSRecName.text = orderData["rec_name"].toString()
                lblOSHpNo.text = orderData["rec_phoneNo"].toString()
                lblOSAddress.text = orderData["address"].toString()
                lblOSUnitFloor.text = orderData["unitFloor"].toString()
                lblOSRemark.text = orderData["remark"].toString()
                var status = orderData["order_status"].toString()
                if (status == "delivered"){
                    lblOSEstimatedTime.text = ""
                        imageViewStatus.setImageResource(R.drawable.image_enjoy_food)
                    }
            }
            override fun onCancelled(p0: DatabaseError) {
            }
        })
        //Read Order Data End

        //Read Payment Data
        val tempList = ArrayList<PaymentModel>()
        val paymentRef = FirebaseDatabase.getInstance().reference.child("Payment")
            .orderByChild("order_id").equalTo(OrderID)
        paymentRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                for (itemSnapShot in p0.children) {

                    val paymentModel = itemSnapShot.getValue<PaymentModel>(PaymentModel::class.java)
                    tempList.add(paymentModel!!)
                        lblOrderTotalShow.text = tempList[0].payment_amount.toString()
                        lblOSPaymentMethodShow.text = tempList[0].payment_method.toString()
                }
            }
            override fun onCancelled(p0: DatabaseError) {
            }
        })
        //Read Payment Data End
    }

    override fun onStart() {
        super.onStart()
        readData()
    }

    override fun onStop() {
        super.onStop()
    }

    /*override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.activity_order_summary, container, false)
        val btnBack: ImageView = view.findViewById(R.id.OSBtnBack)
        btnBack.setOnClickListener{view : View ->
            view.findNavController().navigate(R.id.action_orderSummaryFragment_to_myHistoryFragment)}
        return view
    }

        OSRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            mDatabase = FirebaseDatabase.getInstance().reference
                        adapter = OrderItemHeaderRecyclerAdapter((this.context!!), JsonHelper2(this.context!!).getListData())
        }
    }
     */


}

