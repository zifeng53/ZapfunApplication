package com.example.zapfunfoodorderingapplication.models

class OrderModel{
    var order_id: String? = ""
    var user_id: String? = ""
    var sub_total: Double? = null
    var delivery_fee: Double? = null
    var order_date: String? = ""
    var order_time: String? = ""
    var order_status: String? = ""
    var dish1: String? = ""
    var dish2: String? = ""
    var dish3: String? = ""
    var price1: Double? = null
    var price2: Double? = null
    var price3: Double? = null
    var rice_size: String? = null
    var rice_price : Double? = null
    var rec_name: String? = ""
    var rec_phoneNo: String? = ""
    var address: String? = ""
    var unitFloor: String? = ""
    var remark:String? = ""


    constructor()
        constructor(order_id: String?, user_id: String?, sub_total: Double?, delivery_fee: Double?, order_date: String?, order_time: String?, order_status: String?,
                    dish1: String?, dish2: String?, dish3: String?, price1 :Double?, price2 :Double?, price3 :Double?, rice_size :String?,rice_price : Double?,
                    rec_name: String?, rec_phoneNo: String?, address: String?, unitFloor: String?, remark:String?){
            this.order_id = order_id
            this.user_id = user_id
            this.sub_total = sub_total
            this.delivery_fee = delivery_fee
            this.order_date = order_date
            this.order_time = order_time
            this.order_status = order_status
            this.dish1 = dish1
            this.dish2 = dish2
            this.dish3 = dish3
            this.price1 = price1
            this.price2 = price2
            this.price3 = price3
            this.rice_size = rice_size
            this.rice_price = rice_price
            this.rec_name = rec_name
            this.rec_phoneNo = rec_phoneNo
            this.address = address
            this.unitFloor = unitFloor
            this.remark = remark
        }



}