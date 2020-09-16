package com.example.zapfunfoodorderingapplication.models

class CartModel {
    var user_id:String?=""
    var dish1: String? = ""
    var dish2: String? = ""
    var dish3: String? = ""
    var dish4: String? = ""
    var price1: String? = ""
    var price2: String? = ""
    var price3: String? = ""
    var price4: String? = ""
    var rice_price: String? = ""
    var rice_type: String? = ""

    constructor()
    constructor(user_id:String?, dish1:String, dish2:String, dish3:String, dish4:String,
                price1:String, price2:String, price3:String, price4:String, rice_price:String, rice_type:String){
        this.user_id = user_id
        this.dish1 = dish1
        this.dish2 = dish2
        this.dish3 = dish3
        this.dish4 = dish4
        this.price1 = price1
        this.price2 = price2
        this.price3 = price3
        this.price4 = price4
        this.rice_price = rice_price
        this.rice_type = rice_type
    }
}