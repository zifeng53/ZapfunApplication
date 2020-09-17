package com.example.zapfunfoodorderingapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.zapfunfoodorderingapplication.models.MenuChickenModel
import com.example.zapfunfoodorderingapplication.models.PaymentModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_order_summary.*


class OrderSummaryActivity : AppCompatActivity() {
    //Creating member variables

    var OrderID = ""
    var dish1 = ""
    var dish2 = ""
    var dish3 = ""

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
                lblOSDeliveryFeeShow.text = orderData["delivery_fee"].toString() + ".00"
                lblOSOrderDateTimeShow.text = orderData["order_date"].toString() +", "+orderData["order_time"].toString()
                lblOSRecName.text = orderData["rec_name"].toString()
                lblOSHpNo.text = orderData["rec_phoneNo"].toString()
                lblOSAddress.text = orderData["unitFloor"].toString() +", "+ orderData["address"].toString()
                lblOSRemark.text = orderData["remark"].toString()
                OSItemPrice1.text = orderData["price1"].toString() + "0"
                OSItemPrice2.text = orderData["price2"].toString() + "0"
                OSItemPrice3.text = orderData["price3"].toString() + "0"
                OSItemPrice4.text = orderData["rice_price"].toString()
                OSItem4.text = "Rice: " + orderData["rice_size"].toString()
                dish1 = orderData["dish1"].toString()
                dish2 = orderData["dish2"].toString()
                dish3 = orderData["dish3"].toString()
                var status = orderData["order_status"].toString()
                readDish()

                if (status == "delivering"){
                    lblOSEstimatedTime.text="Estimated Duration: (10 mins)"
                    imageOSstatus.setImageResource(R.drawable.on_theway)
                    imageViewStatus.setImageResource(R.drawable.image_delivering)
                }
                else if (status == "preparing"){
                    lblOSEstimatedTime.text="Estimated Duration: (10 mins)"
                    imageOSstatus.setImageResource(R.drawable.preparing)
                    imageViewStatus.setImageResource(R.drawable.image_preparing)
                }else{ //status delivered
                    lblOSEstimatedTime.text="Delivered"
                    imageOSstatus.setImageResource(R.drawable.pls_enjoy_your_food)
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
                    var paymentMethod= tempList[0].payment_method.toString()
                    lblOSPaymentMethodShow.text = paymentMethod
                    if(paymentMethod == "Cash"){
                        imageOSCashCard.setImageResource(R.drawable.image_cash)
                    }else{
                        imageOSCashCard.setImageResource(R.drawable.image_card)
                    }

                }
            }
            override fun onCancelled(p0: DatabaseError) {
            }
        })
        //Read Payment Data End
    }
    private fun readDish(){
        //Read Dish1 Data
        val dish1List = ArrayList<MenuChickenModel>()
        val dish1Ref = FirebaseDatabase.getInstance().reference.child("Dishes").child("Chicken")
            .orderByChild("dish_id").equalTo(dish1)
        dish1Ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                for (itemSnapShot in p0.children) {
                        val orderModel =
                            itemSnapShot.getValue<MenuChickenModel>(MenuChickenModel::class.java)
                        dish1List.add(orderModel!!)
                        OSItem1.text = dish1List[0].name.toString()


                }
            }
            override fun onCancelled(p0: DatabaseError) {
            }
        })
        //Read Dish1 Data End

        //Read Dish2 Data
        val dish2List = ArrayList<MenuChickenModel>()
        val dish2Ref = FirebaseDatabase.getInstance().reference.child("Dishes").child("Chicken")
            .orderByChild("dish_id").equalTo(dish2)
        dish2Ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                for (itemSnapShot in p0.children) {
                    val orderModel =
                        itemSnapShot.getValue<MenuChickenModel>(MenuChickenModel::class.java)
                        dish2List.add(orderModel!!)
                        OSItem2.text = dish2List[0].name.toString()
                }
            }
            override fun onCancelled(p0: DatabaseError) {
            }
        })
        //Read Dish2 Data End

        //Read Dish3 Data
        val dish3List = ArrayList<MenuChickenModel>()
        val dish3Ref = FirebaseDatabase.getInstance().reference.child("Dishes").child("Chicken")
            .orderByChild("dish_id").equalTo(dish3)
        dish3Ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                for (itemSnapShot in p0.children) {
                    val orderModel =
                        itemSnapShot.getValue<MenuChickenModel>(MenuChickenModel::class.java)
                    dish3List.add(orderModel!!)
                    OSItem3.text = dish3List[0].name.toString()
                }
            }
            override fun onCancelled(p0: DatabaseError) {
            }
        })
        //Read Dish3 Data End
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

