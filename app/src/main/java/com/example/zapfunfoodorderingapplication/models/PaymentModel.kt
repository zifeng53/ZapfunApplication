package com.example.zapfunfoodorderingapplication.models

class PaymentModel {
    var payment_id: String? = ""
    var order_id: String? = ""
    var payment_amount: Double? = 0.00
    var payment_method:String? = ""

    //Default constructor required for calls to
    constructor(){
    }
    constructor(payment_id:String, order_id: String, payment_amount: Double?, payment_method: String){
        this.payment_id = payment_id
        this.order_id = order_id
        this.payment_amount = payment_amount
        this.payment_method = payment_method
    }
}