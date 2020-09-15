package com.example.zapfunfoodorderingapplication

import com.example.zapfunfoodorderingapplication.models.OrderItemModel

class Order {
    var order_id: String? = ""
    var user_id: String? = ""
    var sub_total: String? = ""
    var delivery_fee: String? = ""
    var order_date: String? = ""
    var order_time: String? = ""
    var rec_name: String? = ""
    var rec_phoneNo: String? = ""
    var address: String? = ""
    var unitFloor: String? = ""
    lateinit var order_item: List<OrderItemModel>

    constructor(order_id: String?, user_id: String?, sub_total: String?, delivery_fee: String?,order_date: String?, order_time: String?, rec_name: String?,
                rec_phoneNo: String?, address: String?, unitFloor: String?, order_item: List<OrderItemModel>)


}