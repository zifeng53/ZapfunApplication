package com.example.zapfunfoodorderingapplication.models

class MenuPork1Model {
    var menu_id:String?=null
    var name:String?=null
    var image:String?=null
    var food_type: String?=null
    var price: String?=null

    constructor()
    constructor(menu_id: String?, name: String?, image: String?, food_type: String?, price: String?) {
        this.menu_id = menu_id
        this.name = name
        this.image = image
        this.food_type = food_type
        this.price = price
    }
}