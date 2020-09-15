package com.example.zapfunfoodorderingapplication

class Payment {
    lateinit var payment_id: String
    lateinit var order_id: String
    lateinit var  payment_amount: String
    lateinit var payment_method: String

    //Default constructor required for calls to
    //DataSnapshot.getValue(Payment.class)
    constructor(){
    }
    constructor(payment_id:String, order_id: String, payment_amount: String, payment_method: String){
        this.payment_id = payment_id
        this.order_id = order_id
        this.payment_amount = payment_amount
        this.payment_method = payment_method
    }
}