package com.example.zapfunfoodorderingapplication.models

class CartModel {
    var user_id:String?=""
    var dish1: String? = ""
    var dish2: String? = ""
    var dish3: String? = ""
    var dish4: String? = ""
    var rice_type: String? = ""

    constructor()
    constructor(user_id:String?, dish1:String, dish2:String, dish3:String, dish4:String, rice_type:String){
        this.user_id = user_id
        this.dish1 = dish1
        this.dish2 = dish2
        this.dish3 = dish3
        this.dish4 = dish4
        this.rice_type = rice_type
    }
}