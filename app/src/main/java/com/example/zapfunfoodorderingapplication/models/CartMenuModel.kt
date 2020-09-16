package com.example.zapfunfoodorderingapplication.models

class CartMenuModel {
    var item_id: String?=""
    var user_id: String?=""
    var item: String? = ""
    var price: String? = ""

    constructor()
    constructor(item_id:String?, user_id:String?, item:String, price:String) {
        this.item_id = item_id
        this.user_id = user_id
        this.item = item
        this.price = price
    }
    /*var item1: String? = ""
    var price1: String? = ""
    var item2: String? = ""
    var price2: String? = ""
    var item3: String? = ""
    var price3: String? = ""
    var item4: String? = ""
    var price4: String? = ""
    var user_id: String? = ""

    constructor()
    constructor(item1:String, price1:String, item2:String, price2:String, item3:String, price3:String,
                    item4:String, price4:String, user_id:String) {
        this.item1 = item1
        this.price1 = price1
        this.item2 = item2
        this.price2 = price2
        this.item3 = item3
        this.price3 = price3
        this.item4 = item4
        this.price4 = price4
        this.user_id = user_id
    }*/
}