package com.example.zapfunfoodorderingapplication.models

class OrderModel{
    var order_id: String? = ""
    var user_id: String? = ""
    var sub_total: Int? = null
    var delivery_fee: Int? = null
    var order_date: String? = ""
    var order_time: String? = ""
    var order_status: String? = ""
    var rec_name: String? = ""
    var rec_phoneNo: String? = ""
    var address: String? = ""
    var unitFloor: String? = ""
    lateinit var order_item: List<OrderItemModel>

    constructor()
        constructor(order_id: String?, user_id: String?, sub_total: Int?, delivery_fee: Int?, order_date: String?, order_time: String?, order_status: String?,
                    rec_name: String?, rec_phoneNo: String?, address: String?, unitFloor: String?, order_item: List<OrderItemModel>){
            this.order_id = order_id
            this.user_id = user_id
            this.sub_total = sub_total
            this.delivery_fee = delivery_fee
            this.order_date = order_date
            this.order_time = order_time
            this.order_status = order_status
            this.rec_name = rec_name
            this.rec_phoneNo = rec_phoneNo
            this.address = address
            this.unitFloor = unitFloor
            this.order_item = order_item
        }



}