package com.example.zapfunfoodorderingapplication.models

class OrderItemModel {
    var dish1: String? = ""
    var dish2: String? = ""
    var dish3: String? = ""
    var dish4: String? = ""
    var price1: Int? = null
    var price2: Int? = null
    var price3: Int? = null
    var price4: Int? = null
    var rice_type: String? = ""
    var rice_price: Int? = null

    constructor()
    constructor(dish1:String, dish2:String, dish3:String, dish4:String, price1:Int, price2:Int, price3:Int, price4:Int,
    rice_type:String, rice_price:Int){
        this.dish1 = dish1
        this.dish2 = dish2
        this.dish3 = dish3
        this.dish4 = dish4
        this.price1 = price1
        this.price2 = price2
        this.price3 = price3
        this.price4 = price4
        this.rice_type = rice_type
        this.rice_price = rice_price
    }

}