package com.example.zapfunfoodorderingapplication.models

class CartItemModel {
    var dish1: String? = ""
    var dish2: String? = ""
    var dish3: String? = ""
    var dish4: String? = ""
    var rice_type: String? = ""

    constructor()
    constructor(dish1:String, dish2:String, dish3:String, dish4:String, rice_type:String){
        this.dish1 = dish1
        this.dish2 = dish2
        this.dish3 = dish3
        this.dish4 = dish4
        this.rice_type = rice_type
    }
}